package org.example;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


public class Loan {
    private DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
    private double loanSum;
    private int loanTerm;
    private double interestRate;
    private String interestPeriodType;
    private int interestPeriodFrom;
    private int interestPeriodTo;
    private int paymentDate;
    private Date loanDate;
    private String paymentType;
    private final List<Integer> leapYears = Arrays.asList(2000, 2020, 2040, 2060, 2080,
            2004, 2024, 2044, 2064, 2084,
            2008, 2028, 2048, 2068, 2088,
            2012, 2032, 2052, 2072, 2092,
            2016, 2036, 2056, 2076, 2096);

    public Loan() {
    }

    public Loan(double loanSumIn, int loanTermIn, double interestRateIn,
                String interestPeriodTypeIn, int interestPeriodFromIn, int interestPeriodToIn,
                int paymentDateIn, Date loanDateIn) {
        loanSum = loanSumIn;
        loanTerm = loanTermIn;
        interestRate = interestRateIn / 100;
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

    public List<List<String>> calculateSchedule() {
        if (paymentType == "annuity") {
            return annuity();
        } else if (paymentType == "differential") {
            return differential();
        } else {
            List<List<String>> data = new ArrayList<>();
            List<String> dataRow = Arrays.asList("Не выбран тип платежей.");
            data.add(dataRow);
            return data;
        }
    }

    public void printSchedule(List<List<String>> data) {
        // ШАПКА таблицы
        System.out.println();
        System.out.println("+-----+------------------+--------------+---------------+-----------+-----------------+-------------+");
        System.out.println("|  N  | Дней пользования | Дата платежа | Сумма платежа |  Процент  | Погашаемый долг |   Остаток   |");
        System.out.println("+-----+------------------+--------------+---------------+-----------+-----------------+-------------+");
        System.out.printf("|   0 |                  |              |               |           |                 |  %s |\n", data.get(0).get(6));
        System.out.println("+-----+------------------+--------------+---------------+-----------+-----------------+-------------+");

        // СТРОКА таблицы
        for (int i = 1; i <= loanTerm; i++) {
            System.out.printf("| %s |        %s       |   %s   |      %s |  %s |        %s |  %s |\n",
                    i,
                    data.get(i).get(1),
                    data.get(i).get(2),
                    data.get(i).get(3),
                    data.get(i).get(4),
                    data.get(i).get(5),
                    data.get(i).get(6));
            System.out.println("+-----+------------------+--------------+---------------+-----------+-----------------+-------------+");
        }
    }

    // Рассчет аннуитетных платежей
    public List<List<String>> annuity() {
        List<List<String>> data = new ArrayList<>();       // график платежей в списке

        double interestRateMonth = interestRate / 12;       // процентная ставка в месяц
        double loanLost = loanSum;                          // остаток долга
        double annuityCoef = interestRateMonth / (1 - Math.pow((1 + interestRateMonth), (-(loanTerm-1))));
        double paymentTotalMonth = Math.round(loanLost * annuityCoef * 100.0) / 100.0; // ежемесячный платеж
        int daysBetween;                                    // число дней пользования заемными средствами
        double paymentInterest;                             // сумма процентов
        double paymentLoan;                                 // сумма погашаемого долга

        List<String> dataRow = Arrays.asList("0", "", "", "", "", "", String.format("%.2f", loanLost));
        data.add(dataRow);

        // дата первого платежа
        Date currentDate = (Date) loanDate.clone();
        if (currentDate.getDate() < paymentDate) {
            currentDate.setDate(paymentDate);
            currentDate.setMonth(currentDate.getMonth() - 1);
        } else if (currentDate.getDate() > paymentDate) {
            currentDate.setDate(paymentDate);
        }

        // первый платеж
        currentDate.setMonth(currentDate.getMonth() + 1);
        daysBetween = daysBetween(currentDate, loanDate);                                               // число дней пользования заемными средствами
        paymentInterest = calculatePaymentInterest(loanDate, currentDate, loanLost); // сумма процентов
        paymentLoan = 0;                                              // сумма погашаемого долга
        loanLost -= paymentLoan;
        dataRow = Arrays.asList("1", String.valueOf(daysBetween), formatter.format(currentDate), String.format("%.2f", paymentInterest), String.format("%.2f", paymentInterest), String.format("%.2f", paymentLoan), String.format("%.2f", loanLost));
        data.add(dataRow);

        for (int i = 2; i < loanTerm; i++) {
            loanDate.setDate(loanDate.getDate() + daysBetween);
            currentDate.setMonth(currentDate.getMonth() + 1);
            daysBetween = daysBetween(currentDate, loanDate);                                               // число дней пользования заемными средствами
            paymentInterest = calculatePaymentInterest(loanDate, currentDate, loanLost); // сумма процентов
            paymentLoan = paymentTotalMonth - paymentInterest;                                              // сумма погашаемого долга
            loanLost -= paymentLoan;
            dataRow = Arrays.asList(String.valueOf(i), String.valueOf(daysBetween), formatter.format(currentDate), String.format("%.2f", paymentTotalMonth), String.format("%.2f", paymentInterest), String.format("%.2f", paymentLoan), String.format("%.2f", loanLost));
            data.add(dataRow);
        }

        // Последний платеж
        loanDate.setDate(loanDate.getDate() + daysBetween);
        currentDate.setMonth(currentDate.getMonth() + 1);
        daysBetween = daysBetween(currentDate, loanDate);                                               // число дней пользования заемными средствами

        paymentInterest = calculatePaymentInterest(loanDate, currentDate, loanLost);                    // сумма процентов
        paymentLoan = loanLost;                                                                         // сумма погашаемого долга
        paymentTotalMonth = paymentLoan + paymentInterest;
        loanLost -= paymentLoan;

        dataRow = Arrays.asList(String.valueOf(loanTerm), String.valueOf(daysBetween), formatter.format(currentDate), String.format("%.2f", paymentTotalMonth), String.format("%.2f", paymentInterest), String.format("%.2f", paymentLoan), String.format("%.2f", loanLost));
        data.add(dataRow);
        return data;
    }

    // Расчет Дифференцированных платежей
    public List<List<String>> differential() {
        List<List<String>> data = new ArrayList<>();       // график платежей в списке
        double loanLost = loanSum;                          // остаток долга
        double paymentTotalMonth;                           // ежемесячный платеж
        int daysBetween;                                    // число дней пользования заемными средствами
        double paymentInterest;                             // сумма процентов
        double paymentLoan = Math.round((loanSum / (loanTerm - 1)) * 100.0) / 100.0; // сумма погашаемого долга

        List<String> dataRow = Arrays.asList("0", "", "", "", "", "", String.format("%.2f", loanLost));
        data.add(dataRow);

        // дата первого платежа
        Date currentDate = (Date) loanDate.clone();
        if (currentDate.getDate() < paymentDate) {
            currentDate.setDate(paymentDate);
            currentDate.setMonth(currentDate.getMonth() - 1);
        } else if (currentDate.getDate() > paymentDate) {
            currentDate.setDate(paymentDate);
        }

        currentDate.setMonth(currentDate.getMonth() + 1);           // число дней пользования заемными средствами
        daysBetween = daysBetween(currentDate, loanDate);
        paymentInterest = calculatePaymentInterest(loanDate, currentDate, loanLost);
        dataRow = Arrays.asList("1", String.valueOf(daysBetween), formatter.format(currentDate), String.format("%.2f", paymentInterest), String.format("%.2f", paymentInterest), String.format("%.2f", 0.00), String.format("%.2f", loanLost));
        data.add(dataRow);

        for (int i = 2; i < (loanTerm); i++) {
            loanDate.setDate(loanDate.getDate() + daysBetween);
            currentDate.setMonth(currentDate.getMonth() + 1);
            daysBetween = daysBetween(currentDate, loanDate);
            paymentInterest = calculatePaymentInterest(loanDate, currentDate, loanLost);
            paymentTotalMonth = paymentLoan + paymentInterest;                                              // сумма погашаемого долга
            loanLost -= paymentLoan;                                                                        // остаток долга
            dataRow = Arrays.asList(String.valueOf(i), String.valueOf(daysBetween), formatter.format(currentDate), String.format("%.2f", paymentTotalMonth), String.format("%.2f", paymentInterest), String.format("%.2f", paymentLoan), String.format("%.2f", loanLost));
            data.add(dataRow);
        }

        loanDate.setDate(loanDate.getDate() + daysBetween);
        currentDate.setMonth(currentDate.getMonth() + 1);
        daysBetween = daysBetween(currentDate, loanDate);                                               // число дней пользования заемными средствами

        paymentInterest = calculatePaymentInterest(loanDate, currentDate, loanLost);                    // сумма процентов
        paymentLoan = loanLost;                                                                         // сумма погашаемого долга
        paymentTotalMonth = paymentLoan + paymentInterest;
        loanLost -= paymentLoan;                                                                        // остаток долга
        dataRow = Arrays.asList(String.valueOf(loanTerm), String.valueOf(daysBetween), formatter.format(currentDate), String.format("%.2f", paymentTotalMonth), String.format("%.2f", paymentInterest), String.format("%.2f", paymentLoan), String.format("%.2f", loanLost));
        data.add(dataRow);
        return data;
    }

    // Считаем разницу между датами
    // start не включаем, end включаем
    public int daysBetween(final Date start, final Date end) {
        int dif = 0;
        Date startClone;
        Date endClone;

        if (start.after(end)) {
            endClone = (Date) start.clone();
            startClone = (Date) end.clone();
        } else {
            startClone = (Date) start.clone();
            endClone = (Date) end.clone();
        }

        while (!startClone.equals(endClone)) {
            startClone.setDate(startClone.getDate() + 1);
            dif++;
        }
        return dif;
    }

    // Рассчет процентов
    public double calculatePaymentInterest(Date start, Date end, double loanLost) {
        int daysBetween;
        double paymentInterest;
        int daysYearStart;
        int daysYearEnd;
        if (leapYears.contains(start.getYear() + 1900)) {
            daysYearStart = 366;
        } else {
            daysYearStart = 365;
        }
        if (leapYears.contains(end.getYear() + 1900)) {
            daysYearEnd = 366;
        } else {
            daysYearEnd = 365;
        }

        if (daysYearStart != daysYearEnd) {
            // если переход високосный-невисокосный
            Date endYearDate = (Date) start.clone();
            endYearDate.setDate(31);

            // конец года
            daysBetween = daysBetween(start, endYearDate);
            paymentInterest = Math.round(loanLost * interestRate * daysBetween / daysYearStart * 100.0) / 100.0; // сумма процентов

            // начало года
            daysBetween = daysBetween(endYearDate, end);
            paymentInterest += Math.round(loanLost * interestRate * daysBetween / daysYearEnd * 100.0) / 100.0; // сумма процентов
        } else {
            daysBetween = daysBetween(start, end);
            paymentInterest = Math.round(loanLost * interestRate * daysBetween / daysYearStart * 100.0) / 100.0; // сумма процентов
        }
//        System.out.println(daysBetween);
        return paymentInterest;
    }

    public int getLoanTerm() {
        return loanTerm;
    }

    public void setLoanTerm(int loanTerm) {
        this.loanTerm = loanTerm;
    }

    public String getPaymentType() {
        if(paymentType.equals("differential")){
            return "дифференцированные";
        } else if (paymentType.equals("annuity")) {
            return "аннуитетные";
        }
        return "";
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
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
