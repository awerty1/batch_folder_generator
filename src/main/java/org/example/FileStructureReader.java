package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileStructureReader {
    public static List<String> readStructureFromFile(String filePath) {
        List<String> directories = new ArrayList<>();

        try (InputStream inputStream = FileStructureReader.class.getClassLoader().getResourceAsStream(filePath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            if (inputStream == null) {
                throw new IllegalArgumentException("File not found: " + filePath);
            }

            String line;
            String currentParent = ""; // Текущий родительский каталог

            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.isEmpty()) {
                    continue; // Пропускаем пустые строки
                }

                // Определяем уровень вложенности по количеству отступов
                int indentLevel = getIndentLevel(line);

                // Убираем отступы из строки
                line = line.trim();

                // Если это корневой каталог (без отступов)
                if (indentLevel == 0) {
                    currentParent = line;
                    directories.add(line);
                } else {
                    // Если это вложенный каталог, добавляем его к текущему родителю
                    String fullPath = STR."\{currentParent}/\{line}";
                    directories.add(fullPath);
                }
            }
        } catch (Exception e) {
            System.err.println(STR."Error reading file: \{e.getMessage()}");
        }

        return directories;
    }

    // Метод для определения уровня вложенности по отступам
    private static int getIndentLevel(String line) {
        int indentLevel = 0;
        while (line.charAt(indentLevel) == ' ' || line.charAt(indentLevel) == '\t') {
            indentLevel++;
        }
        return indentLevel;
    }
}
