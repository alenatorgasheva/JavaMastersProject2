package main.windows;

import main.ServiceWindows;

import javax.swing.*;
import java.awt.*;

public class ErrorWind extends JFrame {
    public ErrorWind(String errorText) {
        super(errorText);
        super.setBounds(300, 100, 200, 200);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = super.getContentPane();

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();

        JLabel title = new JLabel("Ошибка: " + errorText);
        panel1.add(title);

        JButton returnButton = new JButton("Обновить");
        returnButton.addActionListener(e -> {
            setVisible(false);
            ServiceWindows.mainWind("EUR");
        });

        JButton endButton = new JButton("Завершить");
        endButton.addActionListener(e -> {
            setVisible(false);
            System.exit(0);
        });

        panel3.add(returnButton);
        panel3.add(endButton);

        container.add(panel1, BorderLayout.NORTH);

        container.add(panel3, BorderLayout.SOUTH);
    }
}
