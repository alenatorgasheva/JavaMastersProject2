package org.example;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class BookTableModel extends AbstractTableModel {

    private int columnCount = 7;
    private ArrayList<String[]> dataArrayList = new ArrayList<String[]>();

    public void BookTableModel() {
        dataArrayList = new ArrayList<String[]>();
        for (int i = 0; i < dataArrayList.size(); i++){
            dataArrayList.add(new String[getColumnCount()]);
        }
    }

    @Override
    public int getRowCount() {
        return dataArrayList.size();
    }

    @Override
    public int getColumnCount() {
        return columnCount;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String[] rows = dataArrayList.get(rowIndex);
        return rows[columnIndex];
    }

    @Override
    public String getColumnName(int columnIndex){
        return switch (columnIndex) {
            case 0 -> "N";
            case 1 -> "Дней пользования";
            case 2 -> "Дата платежа";
            case 3 -> "Сумма платежа";
            case 4 -> "Процент";
            case 5 -> "Погашаемый долг";
            case 6 -> "Остаток";
            default -> "";
        };
    }

    public void addData(List<List<String>> data) {
        String[] rowTable = new String[getColumnCount()];
        for (int i = 0; i < data.size(); i++) {
            rowTable = data.get(i).toArray(new String[0]);
            dataArrayList.add(rowTable);
        }
    }
}
