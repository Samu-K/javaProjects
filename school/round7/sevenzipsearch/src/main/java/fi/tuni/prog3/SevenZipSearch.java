package fi.tuni.prog3;

import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipException;

public class SevenZipSearch {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.err.println("Usage: SevenZipSearch <file.7z> <searchword>");
            System.exit(1);
        }

        String filename = args[0];
        String searchword = args[1];

        try (SevenZFile sevenZFile = new SevenZFile(new File(filename))) {
            SevenZArchiveEntry entry;
            while ((entry = sevenZFile.getNextEntry()) != null) {
                if (entry.isDirectory() || !entry.getName().endsWith(".txt")) {
                    continue;
                }

                System.out.println(entry.getName());

                try (InputStream is = sevenZFile.getInputStream(entry)) {
                    int lineNumber = 0;
                    java.util.Scanner scanner = new java.util.Scanner(is);
                    while (scanner.hasNextLine()) {
                        lineNumber++;
                        String line = scanner.nextLine();
                        if (line.toLowerCase().contains(searchword.toLowerCase())) {
                            System.out.println(lineNumber + ": " + line.replaceAll("(?i)" + searchword, searchword.toUpperCase()));
                        }
                    }
					scanner.close();
                    System.out.println();
                }
            }
        } catch (ZipException e) {
            System.err.println("Error opening file: " + e.getMessage());
            System.exit(1);
        }
    }
}

