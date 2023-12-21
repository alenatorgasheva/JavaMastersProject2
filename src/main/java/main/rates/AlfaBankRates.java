package main.rates;

import main.errors.AlfaBankURLError;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AlfaBankRates extends Rate {
    private String bankName = "Альфа-банк";
    private String URL = "https://alfabank.ru/currency/";
    private double buyRate = 0;
    private double sellRate = 0;

    public void setRates() throws AlfaBankURLError {
        try {
            var document = Jsoup.connect(URL).get();
            var els = document.select("span.a1jIK");
            for (Element element : els) {
                if (element.text().contains(currency)) {
                    int i = els.indexOf(element);
                    buyRate = Double.parseDouble(els.get(i + 1).text().replace(",", "."));
                    sellRate = Double.parseDouble(els.get(i + 2).text().replace(",", "."));
                    break;
                }
            }
        } catch (IOException e) {
            throw new AlfaBankURLError();
        }
    }

    public List<String> getRates() {
        List<String> dataRow = new ArrayList<>();
        dataRow.add(bankName);
        dataRow.add(String.format("%.2f", buyRate));
        dataRow.add(String.format("%.2f", sellRate));
        return dataRow;
    }

    public double getBuyRate() {
        return buyRate;
    }

    public double getSellRate() {
        return sellRate;
    }

    public String getBankName() {
        return bankName;
    }
}
