package main.errors;

public class NegativeInterestRateException extends Throwable {
    @Override
    public String toString() {
        return "Процентная ставка отсутствует или является отрицательной.";
    }
}
