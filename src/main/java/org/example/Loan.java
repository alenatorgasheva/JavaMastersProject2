package org.example;


import java.time.temporal.ChronoUnit;
import java.util.Date;


public class Loan {
    private double loanSum;
    private int loanTerm;
    private double interestRate;
    private String interestPeriodType;
    private int interestPeriodFrom;
    private int interestPeriodTo;
    private int paymentDate;
    private Date loanDate;

    public Loan() {
    }

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

    // Выводит информацию об условиях кредита
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

    // Рассчет аннуитетных платежей
    public void annuity() {
        double loanLost = loanSum;
        double interestRateMonth = interestRate / 12;
        double annuityCoef = (interestRateMonth * (Math.pow((1 + interestRateMonth), loanTerm))) / (Math.pow((1 + interestRateMonth), loanTerm) - 1);
        double paymentTotalMonth = loanSum * annuityCoef; // ежемесячный платеж
        double paymentInterest = loanSum * interestRateMonth / 100; // сумма процентов
        double paymentLoan = paymentTotalMonth - paymentInterest; // сумма погашаемого долга
        loanLost -= paymentLoan; // остаток долга

        Date currentDate = (Date) loanDate.clone(); // дата ближайшего платежа
        // устанавливаем дату первого платежа
        if (currentDate.getDate() < paymentDate) {
            currentDate.setDate(paymentDate);
        } else if (currentDate.getDate() > paymentDate) {
            currentDate.setDate(paymentDate);
            currentDate.setMonth(currentDate.getMonth() + 1);
        }

        // находим число дней пользования заемными средствами
        int daysBetween = daysBetween(currentDate, loanDate);

        // вывод в терминал
        System.out.println();
        System.out.println("+------------------+--------------+---------------+-----------+-----------------+-------------+");
        System.out.println("| Дней пользования | Дата платежа | Сумма платежа |  Процент  | Погашаемый долг |   Остаток   |");
        System.out.println("+------------------+--------------+---------------+-----------+-----------------+-------------+");
        System.out.printf("|        %d        |   %td.%tm.%ty   |    %.2f |  %.2f |      %.2f |  %.2f |\n",
                daysBetween,
                currentDate, currentDate, currentDate,
                paymentTotalMonth,
                paymentInterest,
                paymentLoan,
                loanLost);
        System.out.println("+------------------+--------------+---------------+-----------+-----------------+-------------+");

    }

    // Считаем разницу между датами
    // start не включаем, end включаем
    public int daysBetween(Date start, Date end) {
        int dif = 0;
        if (start.after(end)) {
            Date saveDate = start;
            start = end;
            end = saveDate;
        }

        while (!start.equals(end)) {
            start.setDate(start.getDate() + 1);
            dif++;
        }
        return dif;
    }

    // Расчет Дифференцированных платежей
    public void differential() {
        System.out.println();
    }

    public double getLoanSum() {
        return loanSum;
    }

    public void setLoanSum(double loanSum) {
        this.loanSum = loanSum;
    }


    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public String getInterestPeriodType() {
        return interestPeriodType;
    }

    public void setInterestPeriodType(String interestPeriodType) {
        this.interestPeriodType = interestPeriodType;
    }

    public int getInterestPeriodFrom() {
        return interestPeriodFrom;
    }

    public void setInterestPeriodFrom(int interestPeriodFrom) {
        this.interestPeriodFrom = interestPeriodFrom;
    }

    public int getInterestPeriodTo() {
        return interestPeriodTo;
    }

    public void setInterestPeriodTo(int interestPeriodTo) {
        this.interestPeriodTo = interestPeriodTo;
    }

    public int getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(int paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }
}
