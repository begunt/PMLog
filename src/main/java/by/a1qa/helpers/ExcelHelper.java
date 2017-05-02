package by.a1qa.helpers;

import by.a1qa.model.Report;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static by.a1qa.helpers.CommonData.CommonMethods.getAqaCategoryName;

/**
 * Created by alexei.khilchuk on 10/02/2017.
 */
public class ExcelHelper {
    static Logger LOG = LoggerFactory.getLogger(ExcelHelper.class.getName());

    public static synchronized String exportReportsToFile(List<Report> reportList, String personName) throws IOException {
        Workbook wb = new HSSFWorkbook(ExcelHelper.class.getResourceAsStream("/wl-import-template-file.xls"));
        Sheet sheet = wb.getSheet("WL");
        HSSFCellStyle dateCellStyle = (HSSFCellStyle) wb.createCellStyle();
        short dformat = wb.createDataFormat().getFormat("dd/MM/yyyy");
        dateCellStyle.setDataFormat(dformat);
        Date dateobj = new Date();
        for (int i = 0; i < reportList.size(); i++) {
            Report report = reportList.get(i);
            if (report.getLinkToTask() != null || !Objects.equals(report.getLinkToTask(), "") || !Objects.equals(report.getLinkToTask(), " ")){
                sheet.createRow(i + 1);
                sheet.getRow(i + 1).createCell(0).setCellValue(dateobj);
                sheet.getRow(i + 1).getCell(0).setCellStyle(dateCellStyle);
                sheet.getRow(i + 1).createCell(1).setCellValue(report.getPerson());
                sheet.getRow(i + 1).createCell(2).setCellValue(Integer.valueOf(report.getTime()));
                sheet.getRow(i + 1).createCell(3).setCellValue(report.getLinkToTask());

                String comment = new String("");

                if (report.getActivity() != null) if (!report.getActivity().equals("") && !report.getActivity().equals(" ")) comment = comment.concat("Activity: " + report.getActivity() + '\n');
                if (report.getBuild() != null) if (!report.getBuild().equals("") && !report.getBuild().equals(" ")) comment = comment.concat("Build: " + report.getBuild() + '\n');
                if (report.getEnvironment() != null) if (!Objects.equals(report.getEnvironment(), "") && !Objects.equals(report.getEnvironment(), " ")) comment = comment.concat("Environment: " + report.getEnvironment() + '\n');
                if (report.getDevices() != null) if (!Objects.equals(report.getDevices(), "") && !Objects.equals(report.getDevices(), " ")) comment = comment.concat("Devices: " + report.getDevices() + '\n');
                if (report.getMilestone() != null) if (!Objects.equals(report.getMilestone(), "") && !Objects.equals(report.getMilestone(), " ")) comment = comment.concat("Milestone: " + report.getMilestone() + '\n');
                if (report.getTestruns() != null) if (!Objects.equals(report.getTestruns(), "") && !Objects.equals(report.getTestruns(), " ")) comment = comment.concat("Testruns: " + report.getTestruns() + '\n');
                if (report.getNumberOfCheckedCases() != null) if (!Objects.equals(report.getNumberOfCheckedCases(), "") && !Objects.equals(report.getNumberOfCheckedCases(), " ") && !(report.getNumberOfCheckedCases().equals("0"))) comment = comment.concat("Number of checked cases: " + report.getNumberOfCheckedCases() + '\n');
                if (report.getNumberOfCheckedDefects() != 0) comment = comment.concat("Number of checked defects: " + report.getNumberOfCheckedDefects() + '\n');
                if (report.getNumberOfCheckedStories() != 0) comment = comment.concat("Number of checked stories: " + report.getNumberOfCheckedStories() + '\n');
                if (report.getComment() != null) if (!Objects.equals(report.getComment(), "") && !Objects.equals(report.getComment(), " ")) comment = comment.concat("Comment: " + report.getComment() + '\n');
                if (report.getLink() != null) if (!Objects.equals(report.getLink(), "") && !Objects.equals(report.getLink(), " ")) comment = comment.concat("Link: " + report.getLink());

                sheet.getRow(i + 1).createCell(4).setCellValue(comment);
                sheet.getRow(i + 1).createCell(5).setCellValue(getAqaCategoryName(report.getActivity()));
            }

        }
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy-HH-mm");
        String fileName = String.format("%1s_%2s.xls", personName, df.format(dateobj));
        new File(fileName).createNewFile();
        FileOutputStream fileOut = new FileOutputStream(fileName);
        wb.write(fileOut);
        fileOut.close();
        LOG.info(String.format("File '%s' was created", fileName));
        return fileName;
    }

    public static synchronized List<Report> getReportsHistory(String username) throws IOException {
        List<Report> reportList = new ArrayList<Report>();
        File folder = new File(new File("").getAbsolutePath());
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles != null) {
            Arrays.sort(listOfFiles);
            Collections.reverse(Arrays.asList(listOfFiles));
            for (File file : listOfFiles){
                if (file.getName().contains(username) && file.getName().contains(".xls")){
                    reportList.addAll(getListOfReportsFromExcel(file.getPath()));
                }
            }
        }
        return reportList;
    }

    private static synchronized List<Report> getListOfReportsFromExcel(String filepath) throws IOException {
        List<Report> reportList = new ArrayList<Report>();
        FileInputStream inputStream = new FileInputStream(filepath);
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Workbook wb = new HSSFWorkbook(inputStream);
        Sheet sheet = wb.getSheet("WL");

        Iterator<Row> rowIterator = sheet.iterator();
        if (rowIterator.hasNext()) rowIterator.next();

        while (rowIterator.hasNext()){
            try{
                Report report = new Report();
                Row row = rowIterator.next();
                report.setTimestamp(row.getCell(0).getDateCellValue().toString());
                report.setPerson(row.getCell(1).getStringCellValue());
                report.setTime(String.valueOf(row.getCell(2).getNumericCellValue()));
                report.setLinkToTask((row.getCell(3).getStringCellValue()));

                String[] path = filepath.split("/");
                report.setLink(path[path.length -1]);

                String comment = row.getCell(4).getStringCellValue().replace('\n', '|');
                if (comment.length() > 0)
                    report.setComment(comment.substring(comment.length() - 1).equals("|") ? comment.substring(0, comment.length() - 1) : comment);
                reportList.add(report);
            }
            catch (Exception e){
                LOG.info("Error during reading xls file: " + filepath, e);
            }

        }
        inputStream.close();
        return reportList;
    }
}
