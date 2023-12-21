package main.windows;

import main.ServiceWindows;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RatesWind extends JFrame {

    private final BookTableModel btm = new BookTableModel(); // модель таблицы

    public RatesWind(String currency) {
        super("Сравнение курсов (" + currency + ")");
        super.setBounds(300, 100, 400, 400);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = super.getContentPane();

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();

        JLabel paymentTypeLabel = new JLabel("Валюта:");
        ButtonGroup bg = new ButtonGroup();
        JRadioButton paymentTypeButton1 = new JRadioButton();
        JRadioButton paymentTypeButton2 = new JRadioButton();
        paymentTypeButton1.setText("евро (EUR)");
        paymentTypeButton2.setText("доллар (USD)");
        bg.add(paymentTypeButton1);
        bg.add(paymentTypeButton2);
        if (currency == "EUR") {
            paymentTypeButton1.setSelected(true);
        } else {
            paymentTypeButton2.setSelected(true);
        }

        panel1.add(paymentTypeLabel);
        panel1.add(paymentTypeButton1);
        panel1.add(paymentTypeButton2);

        // таблица
        JTable bookTable = new JTable(btm);
        // прокрутка таблицы
        JScrollPane bookTableScrollPage = new JScrollPane(bookTable);
        bookTableScrollPage.setPreferredSize(new Dimension(400, 100));
        panel2.add(bookTableScrollPage, new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        JButton updateButton = new JButton("Обновить");
        updateButton.addActionListener(e -> {
            setVisible(false);
            if (paymentTypeButton1.isSelected()) {
                ServiceWindows.mainWind("EUR");
            } else {
                ServiceWindows.mainWind("USD");
            }
        });
        panel3.add(updateButton);

        container.add(panel1, BorderLayout.NORTH);
        container.add(panel2, BorderLayout.CENTER);
        container.add(panel3, BorderLayout.SOUTH);
    }

    public void addData(List<List<String>> data) {
        // добавляем данные
        btm.addData(data);
    }

}