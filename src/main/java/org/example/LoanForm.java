package org.example;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

public class LoanForm extends JFrame {

    private String PATHIN = "файл не выбран";
    private String sheetName = "data";
    private String paymentType = "differential";

    public LoanForm() {
        super("График платежей по кредиту");
//        super.setSize(600, 600);
        super.setBounds(300, 100, 400, 600);
        super.setPreferredSize(new Dimension(600, 100));
        super.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 3));
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = super.getContentPane();
        container.setLayout(new GridLayout());

        JLabel fileLabel = new JLabel("Выберите файл");
        JTextField filePATHIN = new JTextField(PATHIN);
        JButton fileButton = new JButton("обзор");
        fileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileFilter() {
                    @Override
                    public boolean accept(File f) {
                        if ((f.getName().endsWith("xlsx")) || (f.isDirectory())) {
                            return true;
                        }
                        return false;
                    }

                    @Override
                    public String getDescription() {
                        return "необходимо использовать файл типа .xlsx";
                    }
                });
                fileChooser.showOpenDialog(new JPanel());
                PATHIN = String.valueOf(fileChooser.getSelectedFile());
                filePATHIN.setText(PATHIN);
            }
        });

        JLabel sheetNameLabel = new JLabel("Название листа:");
        JTextField sheetNameField = new JTextField("data");

        JLabel paymentTypeLabel = new JLabel("Вид платежей:");
        ButtonGroup bg = new ButtonGroup();
        JRadioButton paymentTypeButton1 = new JRadioButton();
        JRadioButton paymentTypeButton2 = new JRadioButton();
        paymentTypeButton1.setText("дифференцированные");
        paymentTypeButton2.setText("аннуитетные");
        bg.add(paymentTypeButton1);
        bg.add(paymentTypeButton2);
        paymentTypeButton1.setSelected(true);

        JButton calcButton = new JButton("Рассчитать");
        calcButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                sheetName = sheetNameField.getText();
                if (paymentTypeButton1.isSelected()) {
                    paymentType = "differential";
                } else {
                    paymentType = "annuity";
                }
//                JOptionPane.showMessageDialog(null, "Выбран файл: " + PATHIN + "\nЛист: " + sheetName);
                ServiceWindows.tableWind(PATHIN, sheetName, paymentType);
            }
        });

        container.add(fileLabel);
        container.add(filePATHIN);
        container.add(fileButton);

        container.add(sheetNameLabel);
        container.add(sheetNameField);

        container.add(paymentTypeLabel);
        container.add(paymentTypeButton1);
        container.add(paymentTypeButton2);

        container.add(calcButton);
    }
}
