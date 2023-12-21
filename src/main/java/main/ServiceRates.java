package main;


import main.errors.AlfaBankURLError;
import main.errors.DVBankURLError;
import main.errors.OpenBankURLError;
import main.rates.AlfaBankRates;
import main.rates.DVBankRates;
import main.rates.OpenBankRates;
import main.rates.Rate;

import java.util.*;


public class ServiceRates {
    AlfaBankRates rate1 = new AlfaBankRates();
    OpenBankRates rate2 = new OpenBankRates();
    DVBankRates rate3 = new DVBankRates();

    public ServiceRates() throws DVBankURLError, AlfaBankURLError, OpenBankURLError {
        updateRates();
    }

    public ServiceRates(String currency) throws DVBankURLError, AlfaBankURLError, OpenBankURLError {
        Rate.setCurrency(currency);
        updateRates();
    }

    private void updateRates() throws AlfaBankURLError, OpenBankURLError, DVBankURLError {
        rate1.setRates();
        rate2.setRates();
        rate3.setRates();
    }

    public List<List<String>> getData() {
        List<List<String>> data = new ArrayList<>();       // наполнение таблицы
        data.add(rate1.getRates());
        data.add(rate2.getRates());
        data.add(rate3.getRates());

        // Сравнение
        List<String> dataRow = new ArrayList<>();
        dataRow.add("Выгоднее");
        // выгодная покупка
        if ((rate1.getBuyRate() <= rate2.getBuyRate()) && (rate1.getBuyRate() <= rate3.getBuyRate())) {
            dataRow.add(rate1.getBankName());
        } else if ((rate2.getBuyRate() <= rate1.getBuyRate()) && (rate2.getBuyRate() <= rate3.getBuyRate())) {
            dataRow.add(rate2.getBankName());
        } else {
            dataRow.add(rate3.getBankName());
        }
        // выгодная продажа
        if ((rate1.getSellRate() >= rate2.getSellRate()) && (rate1.getSellRate() >= rate3.getSellRate())) {
            dataRow.add(rate1.getBankName());
        } else if ((rate2.getSellRate() >= rate3.getSellRate()) && (rate2.getSellRate() >= rate1.getSellRate())) {
            dataRow.add(rate2.getBankName());
        } else {
            dataRow.add(rate3.getBankName());
        }
        data.add(dataRow);
        return data;
    }

    public void setCurrency(String currency) throws DVBankURLError, AlfaBankURLError, OpenBankURLError {
        Rate.setCurrency(currency);
        updateRates();
    }

    public String getCurrency() {
        return Rate.getCurrency();
    }
}
