package by.a1qa.helpers;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/**
 * Created by alexei.khilchuk on 24/09/2016.
 */
public class CommonData {
    private static final String APPLICATION_PROPERTY_FILE = "application.properties";
    private static final PropertiesResourceManager appProperties = new PropertiesResourceManager(APPLICATION_PROPERTY_FILE);

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


    public static class CommonMethods{
        public static float timeToMinParse(String input) {
            float result = 0;
            if (input != null){
                String number = "";
                for (int i = 0; i < input.length(); i++) {
                    char c = input.charAt(i);
                    if (Character.isDigit(c)) {
                        number += c;
                        if (number.length() == input.length()){
                            float tmp = convertToMin(Integer.parseInt(number), 'm');
                            result += tmp;
                            number = "";
                        }
                    } else if (Character.isLetter(c) && !number.isEmpty()) {
                        float tmp = convertToMin(Integer.parseInt(number), c);
                        result += tmp;
                        number = "";
                    }
                }
            }
            return result;
        }

        private static float convertToMin(int value, char unit) {
            switch(unit) {
                case 'd' : return value * 1440;
                case 'h' : return value * 60;
                case 'm' : return (value);
                case 's' : return ((float)value / 60);
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
                if (isGetter(method) && !method.getName().contains("SelectedProject"))
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
    }
}
