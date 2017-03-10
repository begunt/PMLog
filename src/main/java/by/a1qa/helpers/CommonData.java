package by.a1qa.helpers;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by alexei.khilchuk on 24/09/2016.
 */
public class CommonData {
    private static final String APPLICATION_PROPERTY_FILE = "application.properties";
    private static final PropertiesResourceManager appProperties = new PropertiesResourceManager(APPLICATION_PROPERTY_FILE);

    private static final String[] KNOWLEDGE_TRANSFER_ISSUE_INV = appProperties.getProperty("Knowledge_Transfer_/_Product_Investigation").split("#");
    private static final String[] MEETING = appProperties.getProperty("Meeting").split("#");
    private static final String[] OTHER = appProperties.getProperty("Other").split("#");
    private static final String[] PROBATION_PERIOD = appProperties.getProperty("Probation_Period").split("#");
    private static final String[] PROJECT_MANAGEMENT = appProperties.getProperty("Project_management").split("#");
    private static final String[] TEST_DOCUMENTATION_CREATION = appProperties.getProperty("Test_Documentation_Creation").split("#");
    private static final String[] TESTING_ACTIVITIES = appProperties.getProperty("Testing_Activities").split("#");
    /*
    private static Properties appProperties = new Properties();
    static {
        try {
            appProperties.load(new FileInputStream(APPLICATION_PROPERTY_FILE));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**/

    public static final String GOOGLE_SHEET_VALUE_INPUT_OPTION =
            appProperties.getProperty("googleSheetValueInputOption");
    public static final String GOOGLE_SHEET_ID =
            appProperties.getProperty("googleSheetId");
    public static final int REPORT_SEND_EXEC_INTERVAL =
            Integer.valueOf(appProperties.getProperty("reportsSendExecutionInterval"));
    public static final int POSITION_CACHE_RESET_REPORTS_INTERVAL =
            Integer.valueOf(appProperties.getProperty("numOfReportsToResetColumnPositionCache"));
    public static final String ALL_REPORTS_TAB_NAME =
            appProperties.getProperty("allReportsTabName");
    public static final String AQA_JIRA_HOST =
            appProperties.getProperty("aqaJiraHost");
    public static final String AQA_JIRA_CLIENT_SESSION_ATTR = "aqaJiraClient";

    public static class CommonMethods{
        public static int timeToMinParse(String input) {
            int result = 0;
            if (input != null){
                input.replace(',', '.');
                String number = "";
                for (int i = 0; i < input.length(); i++) {
                    char c = input.charAt(i);
                    if (Character.isDigit(c) || c == '.') {
                        number += c;
                        if (i == input.length() - 1){
                            int tmp = convertToMin(Float.valueOf(number), 'm');
                            result += tmp;
                            number = "";
                        }
                    } else if (Character.isLetter(c) && !number.isEmpty()) {
                        int tmp = convertToMin(Float.valueOf(number), c);
                        result += tmp;
                        number = "";
                    }
                }
            }
            return result;
        }

        private static int convertToMin(float value, char unit) {
            switch(unit) {
                case 'd' : return (int) (value * 1440);
                case 'h' : return (int) (value * 60);
                case 'm' : return (int) (value);
                case 's' : return (int) (value / 60);
            }
            return 0;
        }

        public static String getSpreadsheetColumnLetter(int colNum) {
            if (colNum == 0) colNum = 1;
            String[] result = new String[colNum];
            String colName = "";
            for (int i = 0; i < colNum; i++) {
                char c = (char) ('A' + (i % 26));
                colName = c + "";
                if (i > 25) {
                    colName = result[(i / 26) - 1] + "" + c;
                }
                result[i] = colName;
            }
            return result[colNum-1];
        }

        public static ArrayList<Method> findGetters(Class<?> c) {
            ArrayList<Method> list = new ArrayList<Method>();
            Method[] methods = c.getDeclaredMethods();
            for (Method method : methods)
                if (isGetter(method) && !method.getName().contains("SelectedProject") && !method.getName().contains("Password"))
                    list.add(method);
            return list;
        }

        private static boolean isGetter(Method method) {
            if (Modifier.isPublic(method.getModifiers()) &&
                    method.getParameterTypes().length == 0) {
                if (method.getName().matches("^get[A-Z].*") &&
                        !method.getReturnType().equals(void.class))
                    return true;
                if (method.getName().matches("^is[A-Z].*") &&
                        method.getReturnType().equals(boolean.class))
                    return true;
            }
            return false;
        }

        public static String getAqaCategoryName(String nonAqaActivityName){
            String resString;
            if (Arrays.asList(TESTING_ACTIVITIES).contains(nonAqaActivityName.replace(' ', '_')))
                resString = "Testing Activities";
            else if (Arrays.asList(TEST_DOCUMENTATION_CREATION).contains(nonAqaActivityName.replace(' ', '_')))
                resString = "Test Documentation Creation";
            else if (Arrays.asList(KNOWLEDGE_TRANSFER_ISSUE_INV).contains(nonAqaActivityName.replace(' ', '_')))
                resString = "Knowledge Transfer / Product Investigation";
            else if (Arrays.asList(OTHER).contains(nonAqaActivityName.replace(' ', '_')))
                resString = "Other";
            else if (Arrays.asList(PROJECT_MANAGEMENT).contains(nonAqaActivityName.replace(' ', '_')))
                resString = "Project management";
            else if (Arrays.asList(MEETING).contains(nonAqaActivityName.replace(' ', '_')))
                resString = "Meeting";
            else if (Arrays.asList(PROBATION_PERIOD).contains(nonAqaActivityName.replace(' ', '_')))
                resString = "Probation Period";
            else resString = "Other";

            return resString;
        }
    }
}
