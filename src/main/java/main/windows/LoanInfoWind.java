package main.windows;

import main.Loan;
import main.ServiceWindows;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class LoanInfoWind extends JFrame {
    private final DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");

    public LoanInfoWind(Loan loan) {
        super("График платежей по кредиту");
        super.setBounds(300, 100, 300, 300);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = super.getContentPane();

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(1, 2, 5, 0));
        JPanel panel2l = new JPanel();
        panel2l.setLayout(new GridLayout(5, 1, 0, 5));
        JPanel panel2r = new JPanel();
        panel2r.setLayout(new GridLayout(5, 1, 0, 5));
        JPanel panel3 = new JPanel();

        JLabel title = new JLabel("Получено");
        panel1.add(title);

        JLabel l1Field = new JLabel("Сумма кредита:");
        JLabel l2Field = new JLabel("Срок погашения:");
        JLabel l3Field = new JLabel("Ставка:");
        JLabel l4Field = new JLabel("Дата платежа:");
        JLabel l5Field = new JLabel("Дата начала кредита:");

        panel2l.add(l1Field);
        panel2l.add(l2Field);
        panel2l.add(l3Field);
        panel2l.add(l4Field);
        panel2l.add(l5Field);

        JLabel r1Field = new JLabel(String.format(new DecimalFormat("###,###.##").format(loan.getLoanSum())) + " рублей");
        JLabel r2Field = new JLabel(loan.getLoanTerm() + " месяцев");
        JLabel r3Field = new JLabel(String.format("%.1f", loan.getInterestRate() * 100) + " % годовых");
        JLabel r4Field = new JLabel(String.valueOf(loan.getPaymentDate()));
        JLabel r5Field = new JLabel(formatter.format(loan.getLoanDate()));

        panel2r.add(r1Field);
        panel2r.add(r2Field);
        panel2r.add(r3Field);
        panel2r.add(r4Field);
        panel2r.add(r5Field);

        panel2.add(panel2l);
        panel2.add(panel2r);

        JButton returnButton = new JButton("Вернуться");
        returnButton.addActionListener(e -> {
            setVisible(false);
            ServiceWindows.firstWind();
        });

        JButton calcButton = new JButton("Рассчитать");
        calcButton.addActionListener(e -> {
            setVisible(false);
            ServiceWindows.tableWind();
        });

        panel3.add(returnButton);
        panel3.add(calcButton);

        container.add(panel1, BorderLayout.NORTH);
        container.add(panel2, BorderLayout.CENTER);
        container.add(panel3, BorderLayout.SOUTH);
    }
}
