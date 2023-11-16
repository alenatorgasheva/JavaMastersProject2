package org.example;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

public class LoanTable extends JFrame {

    private String PATHOUT = "файл не выбран";
    private BookTableModel btm = new BookTableModel(); // модель таблицы

    public LoanTable(String paymentType, String PATHIN) {
        super("График платежей по кредиту (" + paymentType + " платежи)");
        super.setSize(600, 400);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = super.getContentPane();
        container.setLayout(new GridLayout());

        // таблица
        JTable bookTable = new JTable(btm);

        JLabel fileLabel = new JLabel("Выберите файл");
        PATHOUT = createPATHOUT(PATHIN);
        JTextField filePATHOUT = new JTextField(PATHOUT);
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
                PATHOUT = String.valueOf(fileChooser.getSelectedFile());
                filePATHOUT.setText(PATHOUT);
            }
        });

        JButton endButton = new JButton("завершить");
        endButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        // прокрутка
        JScrollPane bookTableScrollPage = new JScrollPane(bookTable);
        bookTableScrollPage.setPreferredSize(new Dimension(400, 400));

        container.add(bookTableScrollPage, new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));


        container.add(fileLabel);
        container.add(fileButton);
        container.add(endButton);
    }

    private String createPATHOUT(String PATHIN) {
        System.out.println(PATHIN);
        return PATHIN;
    }

    public void addData(List<List<String>> data) {
        // добавляем данные
        btm.addData(data);
    }
}
