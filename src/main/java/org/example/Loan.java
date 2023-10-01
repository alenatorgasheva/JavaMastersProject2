package org.example;

import java.util.Date;

public class Loan {
    private final double loanSum;
    private final int loanTerm;
    private final double interestRate;
    private final String interestPeriodType;
    private final int interestPeriodFrom;
    private final int interestPeriodTo;
    private final int paymentDate;
    private final Date loanDate;

    public Loan(double loanSumIn, int loanTermIn, double interestRateIn,
                String interestPeriodTypeIn, int interestPeriodFromIn, int interestPeriodToIn,
                int paymentDateIn, Date loanDateIn) {
        loanSum = loanSumIn;
        loanTerm = loanTermIn;
        interestRate = interestRateIn;
        interestPeriodType = interestPeriodTypeIn;
        interestPeriodFrom = interestPeriodFromIn;
        interestPeriodTo = interestPeriodToIn;
        paymentDate = paymentDateIn;
        loanDate = loanDateIn;
    }

    public void printInfo() {
        System.out.println("Сумма кредита:\t\t" + loanSum + " рублей.");
        System.out.println("Срок погашения:\t\t" + loanTerm + " месяцев.");
        System.out.println("Процентная ставка:\t" + interestRate + " процентов годовых.");
        System.out.println();
        System.out.println("Процентный период");
        System.out.println("Тип:\t\t\t\t" + interestPeriodType);
        System.out.println("Проценты начисляются с " + interestPeriodFrom + " по " + interestPeriodTo + " число.");
        System.out.println();
        System.out.println("Дата платежа:\t\t" + paymentDate);
        System.out.println("Дата начала кредита: " + loanDate);
    }
}
