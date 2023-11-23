package main.windows;

import javax.swing.*;
import java.awt.*;

public class ErrorWind extends JFrame {
    public ErrorWind(String errorText, boolean addImg) {
        super("Ошибка: " + errorText);
        super.setBounds(300, 100, 200, 200);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = super.getContentPane();

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();

        JLabel title = new JLabel("Ошибка: " + errorText);
        panel1.add(title);

        JButton endButton = new JButton("Завершить");
        endButton.addActionListener(e -> {
            setVisible(false);
            System.exit(0);
        });
        panel3.add(endButton);

        container.add(panel1, BorderLayout.NORTH);

        if (addImg) {
            String cwd = System.getProperty("user.dir");
            JLabel correctTable = new JLabel("Пример корректной таблицы: " + cwd + "\\examples\\example_in.xlsx");
            panel2.add(correctTable);
            container.add(panel2, BorderLayout.CENTER);
        }

        container.add(panel3, BorderLayout.SOUTH);
    }
}
