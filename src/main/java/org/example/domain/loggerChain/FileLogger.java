package org.example.domain.loggerChain;

import java.io.FileWriter;
import java.io.IOException;

public class FileLogger extends AbstractLogger {
    public FileLogger(LogLevel level) {
        super(level);
    }

    @Override
    protected void write(String message) {
        try (FileWriter fw = new FileWriter("log.txt", true)) {
            fw.write(level + ": " + message + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

