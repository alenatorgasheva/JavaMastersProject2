package main.errors;

public class NegativeLoanSumException extends Throwable {
    @Override
    public String toString() {
        return "Сумма кредита отсутствует или является отрицательной.";
    }
}
