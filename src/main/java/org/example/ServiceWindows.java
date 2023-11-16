package org.example;

import java.util.List;

public interface ServiceWindows {

    static void firstWind() {
        LoanForm winForm = new LoanForm();
        winForm.setVisible(true);
        winForm.pack();
    }

    static void tableWind(String PATHIN, String sheetName, String paymentType){
        Loan loan = ServiceXLSX.openXLSX(PATHIN, sheetName);
        loan.setPaymentType(paymentType);
        List<List<String>> data = loan.calculateSchedule();

        LoanTable winTable = new LoanTable("annuity", PATHIN);
        winTable.addData(data);
        winTable.setVisible(true);
        winTable.pack();
    }
}
