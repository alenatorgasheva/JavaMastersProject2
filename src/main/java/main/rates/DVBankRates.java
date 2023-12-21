package main.rates;

import main.errors.DVBankURLError;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DVBankRates extends Rate {
    private String bankName = "Дальневосточный Банк";
    private String URL = "https://www.dvbank.ru/";
    private double buyRate = 0;
    private double sellRate = 0;

    public void setRates() throws DVBankURLError {
        try {
            var document = Jsoup.connect(URL).get();
            var body = document.select("table");
            var els = body.select("tr");
            for (Element element : els) {
                if (element.text().contains("USD")) {
                    var cols = element.select("td");
                    buyRate = Double.parseDouble(cols.get(1).text().substring(2));
                    sellRate = Double.parseDouble(cols.get(2).text().substring(2));
                    break;
                }
            }
        } catch (IOException e) {
            throw new DVBankURLError();
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
