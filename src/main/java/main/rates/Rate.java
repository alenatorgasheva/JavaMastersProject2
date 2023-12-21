package main.rates;

public class Rate {
    static String currency = "USD";

    public static void setCurrency(String newCurrency) {
        currency = newCurrency;
    }

    public static String getCurrency() {
        return currency;
    }
}
