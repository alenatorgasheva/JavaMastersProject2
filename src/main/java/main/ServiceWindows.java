package main;

import main.errors.AlfaBankURLError;
import main.errors.DVBankURLError;
import main.errors.OpenBankURLError;
import main.windows.*;

public class ServiceWindows {
    private static ServiceRates serviceRates;

    static {
        try {
            serviceRates = new ServiceRates();
        } catch (DVBankURLError | AlfaBankURLError | OpenBankURLError e) {
            errorWind(e.toString());
        }
    }

    public static void mainWind(String currency) {
        try {
            serviceRates.setCurrency(currency);
            RatesWind wind = new RatesWind(serviceRates.getCurrency());
            wind.addData(serviceRates.getData());
            wind.setVisible(true);
            wind.pack();
        } catch (DVBankURLError | AlfaBankURLError | OpenBankURLError e) {
            errorWind(e.toString());
        }
    }

    private static void errorWind(String errorText) {
        ErrorWind winErr = new ErrorWind(errorText);
        winErr.setVisible(true);
        winErr.pack();
    }

    public void start() {
        mainWind(serviceRates.getCurrency());
    }
}
