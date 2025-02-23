//package org.example;
//
//import java.io.File;
//import java.util.Scanner;
//
//public class UserInput {
//    public static void getInputAndCreateDirectories() {
//        try (Scanner scanner = new Scanner(System.in)) {
//            boolean continueCreating = true; // Флаг для продолжения цикла
//
//            while (continueCreating) {
//                // Ввод пути для создания директории
//                System.out.print("Enter the path of the directory to create (or type 'exit' to quit): ");
//                String path = scanner.nextLine();
//
//                // Проверка, хочет ли пользователь выйти
//                if (path.equalsIgnoreCase("exit")) {
//                    continueCreating = false;
//                    System.out.println("Exiting the program.");
//                    continue; // Пропускаем оставшуюся часть цикла
//                }
//
//                // Ввод символа для выбора регистра
//                System.out.print("Enter U for uppercase, L for lowercase or D for default: ");
//                String symbol = scanner.nextLine();
//
//                // Создание директории
//                FileOperations.createFileDir(path, symbol);
//            }
//        } catch (Exception e) {
//            System.err.println(STR."An error occurred: \{e.getMessage()}");
//        }
//    }
//}

package org.example;

import java.util.List;
import java.util.Scanner;

public class UserInput {
    public static void getInputAndCreateDirectories() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Choose mode:");
            System.out.println("1. Manual mode");
            System.out.println("2. Create from file");
            System.out.print("Enter your choice (1 or 2): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера

            if (choice == 1) {
                manualMode(scanner);
            } else if (choice == 2) {
                fileMode(scanner);
            } else {
                System.out.println("Invalid choice. Exiting.");
            }
        } catch (Exception e) {
            System.err.println(STR."An error occurred: \{e.getMessage()}");
        }
    }

    private static void manualMode(Scanner scanner) {
        boolean continueCreating = true;

        while (continueCreating) {
            System.out.print("Enter the path of the directory to create (or type 'exit' to quit): ");
            String path = scanner.nextLine();

            if (path.equalsIgnoreCase("exit")) {
                continueCreating = false;
                System.out.println("Exiting the program.");
                continue;
            }

            System.out.print("Enter U for uppercase, L for lowercase or D for default: ");
            String symbol = scanner.nextLine();

            FileOperations.createFileDir(path, symbol);
        }
    }

    private static void fileMode(Scanner scanner) {
        System.out.print("Enter the name of the file in resources (e.g., structure.txt): ");
        String filePath = scanner.nextLine();

        List<String> directories = FileStructureReader.readStructureFromFile(filePath);

        if (directories.isEmpty()) {
            System.out.println("No directories found in the file.");
            return;
        }

        System.out.print("Enter U for uppercase, L for lowercase or D for default: ");
        String symbol = scanner.nextLine();

        for (String dir : directories) {
            FileOperations.createFileDirs(dir, symbol);
        }
    }
}