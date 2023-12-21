package main.errors;

public class OpenBankURLError extends Throwable {
    @Override
    public String toString() {
        return "Проблема с сайтом Банка Открытие.";
    }
}
