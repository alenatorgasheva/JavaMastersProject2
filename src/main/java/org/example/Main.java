package org.example;


import java.util.List;

public class Main {
    public static void main(String[] args) {
//        String PATHIN = "C:/Users/1292354/IdeaProjects/hw1/src/main/resources/example_in.xlsx";
//        String path = PATHIN.substring(0,PATHIN.lastIndexOf(".xlsx"))+"_calc.xlsx";
//        System.out.println(path);

        ServiceWindows service = new ServiceWindows();
        service.start();

//        String PATHIN = "C:/Users/1292354/IdeaProjects/hw1/src/main/resources/example_in.xlsx";
//        String sheetName = "data";
//        String PATHOUT = "C:/Users/1292354/IdeaProjects/hw1/src/main/resources/test.xlsx";
//        Loan loan = ServiceXLSX.openXLSX(PATHIN, sheetName);
//        loan.setPaymentType("annuity");
//        List<List<String>> data = loan.calculateSchedule();

//        Scanner scanner = new Scanner(System.in);
////        System.out.println("Введите путь к таблице с данными: ");
////        System.out.println("Например, C:/Users/1292354/IdeaProjects/hw1/src/main/resources/example_in.xlsx");
////        String PATHIN = scanner.next();
////        System.out.println("Введите название листа с данными:");
////        String sheetName = scanner.next();
//        Loan loan = ServiceXLSX.openXLSX(PATHIN, sheetName);

//        System.out.println("Введите вид платежей (аннуитетный/дифференцированный):");
//        boolean end = false;
//        while (!end) {
//            String paymentType = scanner.next().toLowerCase().strip();
//            if (paymentType.equals("аннуитетный")) {
//                loan.setPaymentType("annuity");
//                end = true;
//            } else if (paymentType.equals("дифференцированный")) {
//                loan.setPaymentType("differential");
//                end = true;
//            } else {
//                System.out.println("Выберите один из предложенных вариантов (аннуитетный/дифференцированный):");
//            }
//        }
//
//        boolean toPrint = false;
//        System.out.println("Вывести таблицу на экран? (да/нет)");
//        String outPrint = scanner.next().toLowerCase().strip();
//        if (!outPrint.equals("нет")) {
//            toPrint = true;
//        }
//
//        boolean toSave = true;
//        System.out.println("Сохранить таблицу в Excel файл? (да/нет)");
//        String outXLSX = scanner.next().toLowerCase().strip();
//        if (!outXLSX.equals("да")) {
//            toSave = false;
//        }

//        System.out.println("Введите путь для сохранения: ");
//        String PATHOUT = scanner.next();
//        PATHOUT += loan.getPaymentType() + ".xlsx";

//        loan.setPaymentType("annuity");
////        loan.setPaymentType("differential");
//        List<List<String>> data = loan.calculateSchedule();
////        System.out.println(data.get(0).get(6));
//        loan.printSchedule(data);
//
//        ServiceXLSX.writeXLSX(PATHOUT, "test", data);
//        scanner.close();
    }
}