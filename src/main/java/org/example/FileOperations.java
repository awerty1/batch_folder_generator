package org.example;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class FileOperations {
    public static void createFileDir(String path, String symbol) {
        String registerSelection_ = registerSelection(path, symbol);
        File dir = new File(registerSelection_);

        if (!dir.exists()) {
            if (dir.mkdir()) {
                System.out.println(STR."Directory \"\{dir}\" is created!");
            } else {
                System.out.println(STR."Failed to create directory \"\{dir}\"!");
            }
        } else {
            System.out.println(STR."Directory \"\{dir}\" already exists!");
        }
    }

    public static void createFileDirs(String path, String symbol) {
        String registerSelection_ = registerSelection(path, symbol);
        File dirs = new File(registerSelection_);

        if (!dirs.exists()) {
            if (dirs.mkdirs()) {
                System.out.println(STR."Multiple directories are created! \{dirs}");
            } else {
                System.out.println(STR."Failed to create multiple directories! \{dirs}");
            }
        } else {
            System.out.println(STR."These directories already exist! \{dirs}");
        }
    }

    public static void reNameFolder(String oldName, String newName) {
        File oldDir = new File(oldName);
        File newDir = new File(newName);

        if (oldDir.exists()) {
            if (oldDir.renameTo(newDir)) {
                System.out.println(STR."Directory was renamed! \{newDir}");
            } else {
                System.out.println(STR."Failed to rename directory! \{oldDir}");
            }
        } else {
            System.out.println(STR."There is no such directory! \{oldDir}");
        }
    }

    public static void deleteFolderOrDir(String folderName) {
        File deleteFolder = new File(folderName);

        if (deleteFolder.exists()) {
            if (deleteFolder.delete()) {
                System.out.println(STR."Directory has been deleted! \{deleteFolder}");
            } else {
                System.out.println(STR."Failed to delete directory!\{deleteFolder}");
            }
        } else {
            System.out.println(STR."There is no such directory! \{deleteFolder}");
        }
    }

    public static void createFile(String fileName) {
        File newFile = new File(fileName);

        if (!newFile.exists()) {
            try {
                if (newFile.createNewFile())
                    System.out.println(STR."File has been created! \{newFile}");
            } catch (IOException ex) {
                System.out.println(STR."Failed to create file! \{newFile}");
                System.out.println(ex.getMessage());
            }
        } else {
            System.out.println(STR."File with the same name already exists! \{newFile}");
        }
    }

    public static void fileSize(String fileName) {
        File fileName_ = new File(fileName);

        if (fileName_.exists()) {
            System.out.println(STR."File size of \{fileName_.getName()}: \{fileName_.length()} bytes");
        } else {
            System.out.println(STR."File with the same name doesn't exist! \{fileName_}");
        }
    }

    public static void lastModified(String path) {
        File file = new File(path);
        String getPath = file.getPath();

        try {
            if (file.exists()) {
                long milliSec = file.lastModified();
                Date date = new Date(milliSec);
                System.out.print(STR."\{getPath} last modified at: \{date}");
            } else {
                System.out.println(STR."There is no such directory or file! \{getPath}");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Создание вложенных каталогов
    public static void createNestedDirectories(String basePath, List<String> nestedPaths, String symbol) {
        for (String path : nestedPaths) {
            String fullPath = STR."\{basePath}/\{path}";
            createFileDirs(fullPath, symbol);
        }
    }

    // Метод для регистрации выбора (преобразование регистра)
    private static String registerSelection(String path, String label) {
        if (label == null) {
            throw new IllegalArgumentException("Label cannot be null.");
        }

        return switch (label.toUpperCase()) {
            case "U" -> path.toUpperCase(); // Преобразование в верхний регистр
            case "L" -> path.toLowerCase(); // Преобразование в нижний регистр
            case "D", "" -> path; // Возвращает исходный путь без изменений
            default -> throw new IllegalArgumentException("Invalid label.");
        };
    }
}
