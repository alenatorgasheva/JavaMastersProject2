package main.errors;

public class PaymentDateOutOfRangeException extends Throwable {
    @Override
    public String toString() {
        return "Некорректная дата платежа.";
    }
}
