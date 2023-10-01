package org.example;

import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
// https://java-online.ru/java-excel-read.xhtml

public interface ServiceXLSX {
    static void openXLSX(final String path) {
        try {
            File file = new File(path);
            XSSFWorkbook wb = (XSSFWorkbook) WorkbookFactory.create(file);      // открываем файл

            XSSFSheet sheet = wb.getSheet("data");                        // открываем лист "data"

            double loanSum = sheet.getRow(0).getCell(CellReference.convertColStringToIndex("B")).getNumericCellValue();
            int loanTerm = (int) sheet.getRow(1).getCell(CellReference.convertColStringToIndex("B")).getNumericCellValue();
            double interestRate = sheet.getRow(2).getCell(CellReference.convertColStringToIndex("B")).getNumericCellValue();
            String interestPeriodType = sheet.getRow(3).getCell(CellReference.convertColStringToIndex("B")).getStringCellValue();
            int interestPeriodFrom = (int) sheet.getRow(3).getCell(CellReference.convertColStringToIndex("D")).getNumericCellValue();
            int interestPeriodTo = (int) sheet.getRow(3).getCell(CellReference.convertColStringToIndex("G")).getNumericCellValue();
            int paymentDate = (int) sheet.getRow(4).getCell(CellReference.convertColStringToIndex("B")).getNumericCellValue();
            Date loanDate = sheet.getRow(5).getCell(CellReference.convertColStringToIndex("B")).getDateCellValue();
            System.out.println(loanSum + 1);
            System.out.println(loanTerm + 1);
            System.out.println(interestRate + 1);
            System.out.println(interestPeriodType);
            System.out.println(interestPeriodFrom + 1);
            System.out.println(interestPeriodTo + 1);
            System.out.println(paymentDate + 1);
            System.out.println(loanDate);

        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }

    }

}
