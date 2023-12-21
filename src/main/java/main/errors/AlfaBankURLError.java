package main.errors;

public class AlfaBankURLError extends Throwable {
    @Override
    public String toString() {
        return "Проблема с сайтом Альфа-банка.";
    }
}
