package org.example;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        String PATH = (String) "C:/Users/1292354/IdeaProjects/hw1/src/main/resources/example_in.xlsx";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите путь к таблице с данными: ");
        System.out.println("Например, C:/Users/1292354/IdeaProjects/hw1/src/main/resources/example_in.xlsx");
        String PATH = scanner.next();

        ServiceXLSX.openXLSX(PATH);

        scanner.close();
    }
}