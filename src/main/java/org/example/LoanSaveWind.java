package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoanSaveWind extends JFrame {
    public LoanSaveWind(String PATHOUT) {
        super("График платежей по кредиту");
        super.setBounds(300, 100, 800, 600);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = super.getContentPane();

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();

        JLabel title = new JLabel("Успешно сохранено!");
        panel1.add(title);

        JLabel text = new JLabel("Ищите здесь: " + PATHOUT);
        panel2.add(text);

        JButton returnButton = new JButton("Вернуться");
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                ServiceWindows.tableWind();
            }
        });

        JButton endButton = new JButton("Завершить");
        endButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                System.exit(0);
            }
        });
        panel3.add(returnButton);
        panel3.add(endButton);

        container.add(panel1, BorderLayout.NORTH);
        container.add(panel2, BorderLayout.CENTER);
        container.add(panel3, BorderLayout.SOUTH);
    }
}
