package org.example;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipOperations {
    public static void toPack(String sourceFile, String zipFile) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(zipFile));
             FileInputStream fis = new FileInputStream(sourceFile)) {

            ZipEntry entry1 = new ZipEntry(sourceFile.substring(sourceFile.lastIndexOf("\\") + 1));
            zout.putNextEntry(entry1);

            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) > 0) {
                zout.write(buffer, 0, len);
            }

            zout.closeEntry();
            System.out.println(STR."File zipped: \{zipFile}");
        } catch (IOException ex) {
            System.err.println(STR."Error during zipping: \{ex.getMessage()}");
        }
    }

    public static void unPack(String zipFile, String destDir) {
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(zipFile))) {
            ZipEntry entry;

            while ((entry = zin.getNextEntry()) != null) {
                String name = entry.getName();

                try (FileOutputStream fout = new FileOutputStream(STR."\{destDir}\\\{name}")) {
                    byte[] buffer = new byte[1024];
                    int len;

                    while ((len = zin.read(buffer)) > 0) {
                        fout.write(buffer, 0, len);
                    }
                    zin.closeEntry();
                    System.out.printf(STR."Unzipped: \{name}\n");
                }
            }
        } catch (IOException ex) {
            System.err.println(STR."Error during unzipping: \{ex.getMessage()}");
        }
    }
}
