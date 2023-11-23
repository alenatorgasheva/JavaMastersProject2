package main;

import main.errors.FileProblemException;
import main.errors.NotCorrectFileException;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

// https://java-online.ru/java-excel-read.xhtml
//https://java-online.ru/java-excel.xhtml


public class ServiceXLSX {
    static Loan openXLSX(String path, String sheetName) throws FileProblemException, NotCorrectFileException {
        try {
            File file = new File(path);
            XSSFWorkbook wb = (XSSFWorkbook) WorkbookFactory.create(file);      // открываем файл

            XSSFSheet sheet = wb.getSheet(sheetName);                        // открываем лист "data"

            double loanSum = sheet.getRow(0).getCell(CellReference.convertColStringToIndex("B")).getNumericCellValue();
            int loanTerm = (int) sheet.getRow(1).getCell(CellReference.convertColStringToIndex("B")).getNumericCellValue();
            double interestRate = sheet.getRow(2).getCell(CellReference.convertColStringToIndex("B")).getNumericCellValue();
            String interestPeriodType = sheet.getRow(3).getCell(CellReference.convertColStringToIndex("B")).getStringCellValue();
            int interestPeriodFrom = (int) sheet.getRow(3).getCell(CellReference.convertColStringToIndex("D")).getNumericCellValue();
            int interestPeriodTo = (int) sheet.getRow(3).getCell(CellReference.convertColStringToIndex("F")).getNumericCellValue();
            int paymentDate = (int) sheet.getRow(4).getCell(CellReference.convertColStringToIndex("B")).getNumericCellValue();
            Date loanDate = sheet.getRow(5).getCell(CellReference.convertColStringToIndex("B")).getDateCellValue();

            Loan loan = new Loan(loanSum, loanTerm, interestRate, interestPeriodType, interestPeriodFrom, interestPeriodTo, paymentDate, loanDate);

            return loan;
        } catch (IOException | InvalidFormatException e) {
            throw new FileProblemException();
        } catch (NullPointerException e) {
            throw new NotCorrectFileException();
        }
    }

    static XSSFSheet createSheet(XSSFWorkbook wb) {
        // настраиваем лист в таблице
        XSSFSheet sheet = wb.createSheet("schedule");
        sheet.setColumnWidth(0, 1000);
        sheet.setColumnWidth(1, 4300);
        sheet.setColumnWidth(2, 4000);
        sheet.setColumnWidth(3, 4000);
        sheet.setColumnWidth(4, 4000);
        sheet.setColumnWidth(5, 4300);
        sheet.setColumnWidth(6, 4000);
        return sheet;
    }

    static void createHeader(XSSFWorkbook wb, XSSFSheet sheet) {
        // настраиваем заголовок таблицы
        XSSFRow header = sheet.createRow(0);

        XSSFCellStyle headerStyle = wb.createCellStyle();

        XSSFFont font = wb.createFont();
        font.setBold(true);
        headerStyle.setFont(font);

        XSSFCell headerCell = header.createCell(0);
        headerCell.setCellValue("N");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(1);
        headerCell.setCellValue("Дней пользования");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(2);
        headerCell.setCellValue("Дата платежа");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(3);
        headerCell.setCellValue("Сумма платежа");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(4);
        headerCell.setCellValue("Процент");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(5);
        headerCell.setCellValue("Погашаемый долг");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(6);
        headerCell.setCellValue("Остаток");
        headerCell.setCellStyle(headerStyle);
    }

    static void addRow(XSSFSheet sheet, Integer rowIndex, List<String> data) {
        // добавляем строку
        XSSFRow row = sheet.createRow(rowIndex);
        for (int i = 0; i <= 6; i++) {
            XSSFCell cell = row.createCell(i);
            cell.setCellValue(data.get(i));
        }
    }

    static void writeXLSX(String path, List<List<String>> data) {
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = createSheet(wb);
        createHeader(wb, sheet);
        for (int i = 0; i < data.size(); i++) {
            addRow(sheet, i+1, data.get(i));
        }

        try (FileOutputStream outputStream = new FileOutputStream(path)) {
            wb.write(outputStream);
            wb.close();
        } catch (IOException e) {
            System.out.println("Возможно у вас открыт файл.");
            throw new RuntimeException(e);
        }

    }
}
