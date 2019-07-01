package com.commons.extras;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DirectoryCreator {

    public static void creatDirectory() {

        if (Files.notExists(Paths.get("C:\\files\\"))) {
            try {
                Files.createDirectories(Paths.get("C:\\files\\"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
