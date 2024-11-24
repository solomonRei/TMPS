package org.example.domain.loggerChain;

public class ErrorLogger extends AbstractLogger {
    public ErrorLogger() {
        super(LogLevel.ERROR);
    }

    @Override
    protected void write(String message) {
        System.err.println("ERROR: " + message);
    }
}

