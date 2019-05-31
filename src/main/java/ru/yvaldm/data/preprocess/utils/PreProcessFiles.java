package ru.yvaldm.data.preprocess.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Walks through XML files and removes all non-unicode characters
 * Creates files with same name in the target directory and prefix "cleared-"
 *
 * @author valery.yakovlev
 */
public class PreProcessFiles {

    private static final Logger log = LoggerFactory.getLogger(PreProcessFiles.class);

    private static final String PATH = "/Users/valery.yakovlev/ml/electroncs";

    public static void main(String[] args) throws Exception {

        final Path dir = Paths.get(PATH);

        for (final File fileEntry : dir.toFile().listFiles()) {
            if (!fileEntry.isDirectory()) {

                log.info(fileEntry.getAbsolutePath());
                Scanner scanner = new Scanner(new File(fileEntry.getAbsolutePath()));
                PrintWriter writer = new PrintWriter(PATH + "/cleared-" + fileEntry.getName(), "UTF-8");

                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String clearLine = StripNonUnicodeChars.stripNonValidXMLCharacters(line);
                    writer.write(clearLine);
                }

                scanner.close();
                writer.close();
            }
        }
    }
}
