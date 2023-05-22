package ru.job4j.io;

import java.io.*;

public final class StoreFile {

    private final File file;

    public StoreFile(File file) {
        this.file = file;
    }

    public synchronized void saveContent(String content) {
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file))) {
            for (int i = 0; i < content.length(); i++) {
                out.write(content.charAt(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}