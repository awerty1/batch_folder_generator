package org.example;

import java.util.Scanner;

public class UserInput {
    public static void getInputAndCreateDirectories() {
        Scanner scanner = new Scanner(System.in);

        // Ввод пути для создания директории
        System.out.print("Enter the path of the directory to create: ");
        String path = scanner.nextLine();

        // Ввод символа для выбора регистра
        System.out.print("Enter U for uppercase, L for lowercase or D for default: ");
        String symbol = scanner.nextLine();

        // Создание директории
        FileOperations.createFileDir(path, symbol);

        // Закрытие сканера
        scanner.close();
    }
}
