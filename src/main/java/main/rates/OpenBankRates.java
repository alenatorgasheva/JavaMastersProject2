package main.rates;

import main.errors.OpenBankURLError;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OpenBankRates extends Rate {
    private String bankName = "Банк Открытие";
    private String URL = "https://www.open.ru/exchange-person";
    private double buyRate = 0;
    private double sellRate = 0;

    public void setRates() throws OpenBankURLError {
        try {
            Document document = Jsoup.connect(URL).get();
            var els = document.select("div[data-react-class=CurrencyExchange]");
            String element = String.valueOf(els.get(0).attr("data-react-props"));
            int i = element.indexOf("\"from\":\"" + currency + "\",\"to\":\"RUB\"");
            element = element.substring((i - 1), (i + 114));
            i = element.indexOf("salePrice");
            buyRate = Double.parseDouble(element.substring((i + 11), (i + 15)));
            i = element.indexOf("purchasePrice");
            sellRate = Double.parseDouble(element.substring((i + 15), (i + 19)));
        } catch (IOException e) {
            throw new OpenBankURLError();
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
