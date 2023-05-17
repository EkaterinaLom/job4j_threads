package ru.job4j.thread;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.net.URL;

public class Wget implements Runnable {
    private final String url;
    private final int speed;
    private final String nameFile;

    public Wget(String url, int speed, String nameFile) {
        this.url = url;
        this.speed = speed;
        this.nameFile = nameFile;
    }

    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream out = new FileOutputStream(nameFile)) {
            byte[] dataBuffer = new byte[1024];
            int batesRead;
            int downloads = 0;
            long start = System.currentTimeMillis();
            while ((batesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                downloads += batesRead;
                if (downloads >= speed) {
                    long delta = System.currentTimeMillis() - start;
                    if (delta < 1000) {
                        Thread.sleep(1000 - delta);
                    }
                    start = System.currentTimeMillis();
                    downloads = 0;
                }
                out.write(dataBuffer, 0, batesRead);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        if (args.length != 3) {
            throw new IllegalArgumentException("Check the number of arguments specified. There must be 2 of them.");
        }
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        String nameFile = args[2];
        Thread wget = new Thread(new Wget(url, speed, nameFile));
        wget.start();
        wget.join();
    }
}
