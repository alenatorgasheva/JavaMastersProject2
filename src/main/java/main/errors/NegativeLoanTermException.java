package main.errors;

public class NegativeLoanTermException extends Throwable {
    @Override
    public String toString() {
        return "Срок кредита отсутствует или является отрицательным.";
    }
}
