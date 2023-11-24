package main;

import main.errors.*;
import main.windows.*;

public class ServiceWindows {

    private static String PATHIN = "файл не выбран";
    private static String PATHOUT = "файл не выбран";
    private static Loan loan = new Loan();

    public static void formWind() {
        LoanFormWind winForm = new LoanFormWind(PATHIN);
        winForm.setVisible(true);
        winForm.pack();
    }

    public static void tableWind(){
        LoanTableWind winTable = new LoanTableWind(loan.getPaymentType());
        winTable.addData(loan.calculateSchedule());
        winTable.setVisible(true);
        winTable.pack();
    }

    public static void loanInfoWind(String pathIn, String sheetName, String paymentType) {
        PATHIN = pathIn;
        try {
            loan = ServiceXLSX.openXLSX(PATHIN, sheetName);
            loan.setPaymentType(paymentType);

            LoanInfoWind winInfo = new LoanInfoWind(loan);
            winInfo.setVisible(true);
            winInfo.pack();
        } catch (FileProblemException | NegativeLoanTermException | NegativeInterestRateException |
                 NegativeLoanSumException | PaymentDateOutOfRangeException e) {
            errorWind(e.toString(), false);
        }  catch (NotCorrectFileException e) {
            errorWind(e.toString(), true);
        }
    }

    private static void errorWind(String errorText, boolean addImg) {
        ErrorWind winErr = new ErrorWind(errorText, addImg);
        winErr.setVisible(true);
        winErr.pack();
    }

    public static void saveWind() {
        createPATHOUT();
        ServiceXLSX.writeXLSX(PATHOUT, loan.calculateSchedule());

        LoanSaveWind winSave = new LoanSaveWind(PATHOUT);
        winSave.setVisible(true);
        winSave.pack();
    }

    private static void createPATHOUT() {
        PATHOUT = PATHIN.substring(0, PATHIN.lastIndexOf(".xlsx")) + "_calc.xlsx";
    }

    public void start() {
        formWind();
    }
}
