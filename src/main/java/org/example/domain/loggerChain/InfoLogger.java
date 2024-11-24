package org.example.domain.loggerChain;

public class InfoLogger extends AbstractLogger {
    public InfoLogger() {
        super(LogLevel.INFO);
    }

    @Override
    protected void write(String message) {
        System.out.println("INFO: " + message);
    }
}

