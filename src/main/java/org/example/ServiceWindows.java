package org.example;

public class ServiceWindows {

    private static String PATHIN = "файл не выбран";
    private static String PATHOUT = "файл не выбран";
    private static Loan loan = new Loan();

    static void firstWind() {
        LoanFormWind winForm = new LoanFormWind();
        winForm.setVisible(true);
        winForm.pack();
    }

    static void tableWind(){
        LoanTableWind winTable = new LoanTableWind(loan.getPaymentType(), PATHIN);
        winTable.addData(loan.calculateSchedule());
        winTable.setVisible(true);
        winTable.pack();
    }

    static void loanInfoWind(String pathIn, String sheetName, String paymentType) {
        PATHIN = pathIn;
        loan = ServiceXLSX.openXLSX(PATHIN, sheetName);
        loan.setPaymentType(paymentType);

        LoanInfoWind winInfo = new LoanInfoWind(loan);
        winInfo.setVisible(true);
        winInfo.pack();
    }

    public static void saveWind() {
        createPATHOUT();
        ServiceXLSX.writeXLSX(PATHOUT, "schedule", loan.calculateSchedule());

        LoanSaveWind winSave = new LoanSaveWind(PATHOUT);
        winSave.setVisible(true);
        winSave.pack();
    }

    private static void createPATHOUT() {
        PATHOUT = PATHIN.substring(0, PATHIN.lastIndexOf(".xlsx")) + "_calc.xlsx";
    }

    public void start() {
        firstWind();
    }
}
