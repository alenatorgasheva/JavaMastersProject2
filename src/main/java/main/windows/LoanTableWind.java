package main.windows;

import main.ServiceWindows;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class LoanTableWind extends JFrame {

    private final BookTableModel btm = new BookTableModel(); // модель таблицы

    public LoanTableWind(String paymentType) {
        super("График платежей по кредиту (" + paymentType + " платежи)");
        super.setBounds(300, 100, 800, 600);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = super.getContentPane();

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();

        JLabel paymentTypeLabel = new JLabel("Вид платежей: " + paymentType);
        panel1.add(paymentTypeLabel);

        // таблица
        JTable bookTable = new JTable(btm);
        // прокрутка таблицы
        JScrollPane bookTableScrollPage = new JScrollPane(bookTable);
        bookTableScrollPage.setPreferredSize(new Dimension(800, 400));
        panel2.add(bookTableScrollPage, new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        JButton saveButton = new JButton("Сохранить");
        saveButton.addActionListener(e -> {
            setVisible(false);
            ServiceWindows.saveWind();
        });

        JButton endButton = new JButton("Завершить");
        endButton.addActionListener(e -> {
            setVisible(false);
            System.exit(0);
        });
        panel3.add(saveButton);
        panel3.add(endButton);

        container.add(panel1, BorderLayout.NORTH);
        container.add(panel2, BorderLayout.CENTER);
        container.add(panel3, BorderLayout.SOUTH);
    }

    public void addData(List<List<String>> data) {
        // добавляем данные
        btm.addData(data);
    }
}
