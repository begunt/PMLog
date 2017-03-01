package by.a1qa.helpers;

import by.a1qa.model.Report;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static by.a1qa.helpers.CommonData.CommonMethods.getAqaCategoryName;

/**
 * Created by alexei.khilchuk on 10/02/2017.
 */
public class ExcelExporter {
    static Logger LOG = LoggerFactory.getLogger(ExcelExporter.class.getName());

    public static synchronized String exportReportsToFile(List<Report> reportList) throws IOException {
        Workbook wb = new HSSFWorkbook(ExcelExporter.class.getResourceAsStream("/wl-import-template-file.xls"));
        Sheet sheet = wb.getSheet("WL");
        HSSFCellStyle dateCellStyle = (HSSFCellStyle) wb.createCellStyle();
        short dformat = wb.createDataFormat().getFormat("dd/MM/yyyy");
        dateCellStyle.setDataFormat(dformat);
        Date dateobj = new Date();
        for (int i = 0; i < reportList.size(); i++) {
            Report report = reportList.get(i);
            if (report.getLinkToTask() != null || report.getLinkToTask() != "" || report.getLinkToTask() != " ")
            sheet.createRow(i + 1);
            sheet.getRow(i + 1).createCell(0).setCellValue(dateobj);
            sheet.getRow(i + 1).getCell(0).setCellStyle(dateCellStyle);
            sheet.getRow(i + 1).createCell(1).setCellValue(report.getPerson());
            sheet.getRow(i + 1).createCell(2).setCellValue(Integer.valueOf(report.getTime()));
            sheet.getRow(i + 1).createCell(3).setCellValue(report.getLinkToTask());
            sheet.getRow(i + 1).createCell(4).setCellValue(report.getComment());
            sheet.getRow(i + 1).createCell(5).setCellValue(getAqaCategoryName(report.getActivity()));
        }
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy-HH-mm");
        String fileName = String.format("%1s_%2s.xls",reportList.get(0).getPerson(), df.format(dateobj));
        new File(fileName).createNewFile();
        FileOutputStream fileOut = new FileOutputStream(fileName);
        wb.write(fileOut);
        fileOut.close();
        LOG.info(String.format("File '%s' was created", fileName));
        return fileName;
    }
}
