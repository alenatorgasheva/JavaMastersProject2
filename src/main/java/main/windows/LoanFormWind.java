package main.windows;

import main.ServiceWindows;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.io.File;

public class LoanFormWind extends JFrame {
    private String PATHIN = "файл не выбран";
    private String sheetName = "data";
    private String paymentType = "differential";

    public LoanFormWind(String newPATHIN) {
        super("График платежей по кредиту");
        super.setBounds(300, 100, 400, 600);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        if (!newPATHIN.equals("")){
            PATHIN = newPATHIN;
        }

        Container container = super.getContentPane();
        container.setLayout(new GridLayout(4,1,5,5));

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();

        JLabel fileLabel = new JLabel("Выберите файл");
        JTextField filePATHIN = new JTextField(PATHIN);
        filePATHIN.setPreferredSize(new Dimension(300, 20));
        JButton fileButton = new JButton("обзор");
        fileButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileFilter() {
                @Override
                public boolean accept(File f) {
                    return (f.getName().endsWith("xlsx")) || (f.isDirectory());
                }

                @Override
                public String getDescription() {
                    return "необходимо использовать файл типа .xlsx";
                }
            });
            fileChooser.showOpenDialog(new JPanel());
            PATHIN = String.valueOf(fileChooser.getSelectedFile());
            filePATHIN.setText(PATHIN);
        });

        JLabel sheetNameLabel = new JLabel("Название листа:");
        JTextField sheetNameField = new JTextField("data");
        sheetNameField.setPreferredSize(new Dimension(350, 20));

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
        calcButton.addActionListener(e -> {
            setVisible(false);
            sheetName = sheetNameField.getText();
            if (paymentTypeButton1.isSelected()) {
                paymentType = "differential";
            } else {
                paymentType = "annuity";
            }
            ServiceWindows.loanInfoWind(PATHIN, sheetName, paymentType);
        });

        panel1.add(fileLabel);
        panel1.add(filePATHIN);
        panel1.add(fileButton);

        panel2.add(sheetNameLabel);
        panel2.add(sheetNameField);

        panel3.add(paymentTypeLabel);
        panel3.add(paymentTypeButton1);
        panel3.add(paymentTypeButton2);

        panel4.add(calcButton);

        container.add(panel1);
        container.add(panel2);
        container.add(panel3);
        container.add(panel4);
    }
}
