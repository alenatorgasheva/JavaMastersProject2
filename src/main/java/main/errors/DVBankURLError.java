package main.errors;

public class DVBankURLError extends Throwable {
    @Override
    public String toString() {
        return "Проблема с сайтом Дальневосточного банка.";
    }
}
