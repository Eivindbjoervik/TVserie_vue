package org.example.model;

import java.io.FileWriter;
import java.io.IOException;

public class WriteToFileThread extends Thread {
    private final String filePath;

    public WriteToFileThread(String filePath) {
        this.filePath = filePath;
    }

    public void run() {
        try {
            FileWriter writer = new FileWriter(filePath);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}