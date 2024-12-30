package org.example;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class FileOperations {
    public static void createFileDir(String path, String symbol) {
        String registerSelection_ = registerSelection(path, symbol);
        File dir = new File(registerSelection_);
        if (!dir.exists()) {
            if (dir.mkdir()) {
                System.out.println("Directory is created! " + dir);
            } else {
                System.out.println("Failed to create directory! " + dir);
            }
        } else {
            System.out.println("This directory already exists! " + dir);
        }
    }

    public static void createFileDirs(String path, String symbol) {
        String registerSelection_ = registerSelection(path, symbol);
        File dirs = new File(registerSelection_);
        if (!dirs.exists()) {
            if (dirs.mkdirs()) {
                System.out.println("Multiple directories are created! " + dirs);
            } else {
                System.out.println("Failed to create multiple directories! " + dirs);
            }
        } else {
            System.out.println("These directories already exist! " + dirs);
        }
    }

    public static void reNameFolder(String oldName, String newName) {
        File oldDir = new File(oldName);
        File newDir = new File(newName);
        if (oldDir.exists()) {
            if (oldDir.renameTo(newDir)) {
                System.out.println("Directory was renamed! " + newDir);
            } else {
                System.out.println("Failed to rename directory! " + oldDir);
            }
        } else {
            System.out.println("There is no such directory! " + oldDir);
        }
    }

    public static void deleteFolderOrDir(String folderName) {
        File deleteFolder = new File(folderName);
        if (deleteFolder.exists()) {
            if (deleteFolder.delete()) {
                System.out.println("Directory has been deleted! " + deleteFolder);
            } else {
                System.out.println("Failed to delete directory!" + deleteFolder);
            }
        } else {
            System.out.println("There is no such directory! " + deleteFolder);
        }
    }

    public static void createFile(String fileName) {
        File newFile = new File(fileName);
        if (!newFile.exists()) {
            try {
                if (newFile.createNewFile())
                    System.out.println("File has been created! " + newFile);
            } catch (IOException ex) {
                System.out.println("Failed to create file! " + newFile);
                System.out.println(ex.getMessage());
            }
        } else {
            System.out.println("File with the same name already exists! " + newFile);
        }
    }

    public static void fileSize(String fileName) {
        File fileName_ = new File(fileName);
        if (fileName_.exists()) {
            System.out.println("File size of " + fileName_.getName() + ": " + fileName_.length() + " bytes");
        } else {
            System.out.println("File with the same name doesn't exist! " + fileName_);
        }
    }

    public static void lastModified(String path) {
        File file = new File(path);
        long millisec;
        String getPath = file.getPath();

        try {
            if(file.exists()) {
                millisec = file.lastModified();
                Date date = new Date(millisec);
                System.out.print(getPath + " last modified at: " + date);
            } else {
                System.out.println("There is no such directory or file! " + getPath);
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    // Метод для регистрации выбора (преобразование регистра)
    private static String registerSelection(String path, String label) {
        switch (label) {
            case "U":
                return path.toUpperCase();
            case "L":
                return path.toLowerCase();
            case "D":
            case "":
            default:
                return path;
        }
    }
}
