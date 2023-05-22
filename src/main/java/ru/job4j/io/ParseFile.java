package ru.job4j.io;

import java.io.*;
import java.util.function.Predicate;

public final class ParseFile {

    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }

    public String content(Predicate<Character> filter) {
        StringBuilder out = new StringBuilder();
        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file))) {
            int data;
            while ((data = inputStream.read()) != -1) {
                if (filter.test((char) data)) {
                    out.append((char) data);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public String getContentWithoutUnicode() {
        return content(data -> data <= 0x80);
    }

    public String getContent() {
        return content(data -> true);
    }
}
