package by.a1qa.helpers;

import by.a1qa.model.Report;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import static by.a1qa.helpers.CommonData.CommonMethods.findGetters;
import static by.a1qa.helpers.CommonData.CommonMethods.getSpreadsheetColumnLetter;

/**
 * Created by alexei.khilchuk on 27/01/2017.
 */
public class SpreadsheetHelper {
    private static Logger LOG = LoggerFactory.getLogger(SpreadsheetHelper.class.getName());
    private static String spreadsheetId = CommonData.GOOGLE_SHEET_ID;
    private static HashMap<String, HashMap<String, Integer>> productColumnsMap = new HashMap<String, HashMap<String, Integer>>();

    private static final String APPLICATION_NAME ="DR";
    /** Directory to store user credentials for this application. */
    private static final java.io.File DATA_STORE_DIR = new java.io.File(
            System.getProperty("user.home"), ".credentials/sheets.googleapis.dr");

    /** Global instance of the {@link FileDataStoreFactory}. */
    private static FileDataStoreFactory DATA_STORE_FACTORY;

    /** Global instance of the JSON factory. */
    private static final JsonFactory JSON_FACTORY =
            JacksonFactory.getDefaultInstance();

    /** Global instance of the HTTP transport. */
    private static HttpTransport HTTP_TRANSPORT;

    /** Global instance of the scopes required by this quickstart.
     *
     * If modifying these scopes, delete your previously saved credentials
     * at ~/.credentials/sheets.googleapis.com-java-quickstart
     */
    private static final List<String> SCOPES =
            Arrays.asList(SheetsScopes.SPREADSHEETS);

    static {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Creates an authorized Credential object.
     * @return an authorized Credential object.
     * @throws IOException
     */
    private static Credential authorize() throws IOException {
        // Load client secrets.
        InputStream in =
                SpreadsheetHelper.class.getResourceAsStream("/client_secret.json");
        GoogleClientSecrets clientSecrets =
                GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow =
                new GoogleAuthorizationCodeFlow.Builder(
                        HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                        .setDataStoreFactory(DATA_STORE_FACTORY)
                        .setAccessType("offline")
                        .build();
        Credential credential = new AuthorizationCodeInstalledApp(
                flow, new LocalServerReceiver()).authorize("user");
        return credential;
    }

    /**
     * Build and return an authorized Sheets API client service.
     * @return an authorized Sheets API client service
     * @throws IOException
     */
    private static Sheets getSheetsService() throws IOException {
        Credential credential = authorize();
        return new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                    .setApplicationName(APPLICATION_NAME)
                    .build();
    }


    /**
     * Returns a row number without any records in columns
     * @param productName a worksheet tab name
     * @return a number of the row (1-based number)
     * @throws IOException
     */
    private static int getLastRowPosition(String productName) throws IOException {
        Sheets service = getSheetsService();
        String range = productName + "!A:Z";
        ValueRange response = service.spreadsheets().values()
                .get(spreadsheetId, range)
                .execute();
        List<List<Object>> values = response.getValues();
        LOG.info(String.format("Last row for '%s' product is '%d", productName, values.size() + 1));
        return values.size() + 1;
    }

    /**
     * Returns column number with the required name
     * If it's absent - creates it as the last record in the first row
     * @param productName a worksheet tab name
     * @param columnName a name of the column (cells in the first row)
     * @return - column position (1-based number)
     * @throws IOException
     */
    private static int getColumnPosition(String productName, String columnName, boolean addColumnIfAbsent) throws IOException {
        int columnPosition = 0;

        boolean wasNotFound = true;
        Sheets service = getSheetsService();
        String range = productName + "!A:Z";
        ValueRange response = service.spreadsheets().values()
                .get(spreadsheetId, range)
                .execute();
        List<List<Object>> values = response.getValues();
        if (values != null || values.size() != 0) {
            for (Object cell: values.get(0)){
                columnPosition++;
                if (cell.equals(columnName)){
                    wasNotFound = false;
                    break;
                }
            }
        }
        if (wasNotFound){
            if (addColumnIfAbsent){
                columnPosition++;
                addCell(productName, 1, columnPosition,columnName);
            }
            else{
                columnPosition = 0;
                LOG.info(String.format("Column '%s' position was not found",columnName));
            }
        }
        LOG.info(String.format("Column '%s' position for '%s' product is '%d'",columnName, productName,columnPosition));
        return columnPosition;
    }

    /**
     * Returns column number with the required name using cache hashmap
     * If it's absent - creates it as the last record in the first row
     * @param productName a worksheet tab name
     * @param columnName a name of the column (cells in the first row)
     * @return - column position (1-based number)
     * @throws IOException
     */
    private static int getColumnPositionCached(String productName, String columnName, boolean addColumnIfAbsent) throws IOException {
        int columnPosition;
        if (productColumnsMap.containsKey(productName)){
            HashMap<String, Integer> columnPositionMap = productColumnsMap.get(productName);
            if (columnPositionMap.containsKey(columnName)){
                columnPosition = columnPositionMap.get(columnName);
            }
            else {
                columnPosition = getColumnPosition(productName, columnName, addColumnIfAbsent);
                columnPositionMap.put(columnName, columnPosition);
            }
        }
        else {
            columnPosition = getColumnPosition(productName, columnName, addColumnIfAbsent);
            HashMap<String, Integer> columnPositionMap = new HashMap<String, Integer>();
            columnPositionMap.put(columnName, columnPosition);
            productColumnsMap.put(productName, columnPositionMap);
        }

        return columnPosition;
    }

    /**
     * Adds the value to the cell
     * @param productName a worksheet tab name
     * @param row a row of the cell
     * @param column a column of the cell
     * @param value a value to fill the cell
     * @throws IOException
     */
    private static void addCell(String productName, int row, int column, Object value) throws IOException {
        Sheets service = getSheetsService();
        String range =
                productName
                + "!"
                + getSpreadsheetColumnLetter(column).replace(" ", "")
                + String.valueOf(row).replace(" ", "")
                + ":"
                + getSpreadsheetColumnLetter(column).replace(" ", "");
        ArrayList<Object> cellList = new ArrayList<>();
        cellList.add(value);
        ArrayList<List<Object>> rowList = new ArrayList<>();
        rowList.add(cellList);
        service.spreadsheets().values()
                .update(spreadsheetId, range, new ValueRange().setValues(rowList))
                .set("valueInputOption", CommonData.GOOGLE_SHEET_VALUE_INPUT_OPTION)
                .execute();
        LOG.info(String.format("Cell '%s' with position '%s' for product '%s' was pasted",String.valueOf(value), range, productName));
    }

    /**
     * Send report to the Spreadsheet
     * @param report a report to send to the Spreadsheet
     * @param productName a name of the product (Worksheet)
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws IOException
     */
    public static void submitReport(Report report, String productName, boolean addColumnIfAbsent) throws InvocationTargetException, IllegalAccessException, IOException {
        List<Method> methods = findGetters(Report.class);
        TreeMap<Integer, Object> columnsPositionMap = new TreeMap<Integer, Object>();
        for (Method method: methods){
            String columnName = method.getName().replace("get", "");
            int colForMethod = getColumnPositionCached(productName, columnName, addColumnIfAbsent);
            if (colForMethod != 0){
                columnsPositionMap.put(colForMethod, method.invoke(report,null));
            }

        }
        int rowForReport = getLastRowPosition(productName);
        LOG.info(String.format("Submitting report of '%s' to the product '%s'",report.getPerson(), productName));
        addRow(productName, rowForReport, new ArrayList<Object>(columnsPositionMap.values()));
    }

    /**
     * Adding a row of cells to the Shreadsheet
     * @param productName a Worksheet name
     * @param rowPosition a position of the row to paste row
     * @param row a list with cells data
     * @throws IOException
     */
    private static void addRow(String productName, int rowPosition, List<Object> row) throws IOException {
        Sheets service = getSheetsService();
        String range =
                productName
                + "!A"
                + String.valueOf(rowPosition).replace(" ", "")
                + ":Z";
        ArrayList<List<Object>> rowList = new ArrayList<>();
        rowList.add(row);
        service.spreadsheets().values()
                .update(spreadsheetId, range, new ValueRange().setValues(rowList))
                .set("valueInputOption", CommonData.GOOGLE_SHEET_VALUE_INPUT_OPTION)
                .execute();
        LOG.info(String.format("Row '%s' with position '%d' for product '%s' was pasted", row.toString(), rowPosition, productName));
    }

    /**
     * Clears hash map with column indexes
     */
    public static void clearColumnPositionCache(){
        productColumnsMap.clear();
    }
}
