package org.example;

import java.security.Provider;

public class Main {
    public static void main(String[] args) {
        String PATH = (String) "C:/Users/1292354/IdeaProjects/hw1/src/main/resources/example_in.xlsx";
        ServiceXLSX.openXLSX(PATH);
        System.out.println("--- END ---");
    }
}