package org.example.domain.loggerChain;

public abstract class AbstractLogger implements Logger {
    protected LogLevel level;
    protected Logger nextLogger;

    public AbstractLogger(LogLevel level) {
        this.level = level;
    }

    @Override
    public void setNext(Logger nextLogger) {
        this.nextLogger = nextLogger;
    }

    @Override
    public void log(String message, LogLevel level) {
        if (this.level == level) {
            write(message);
        }
        if (nextLogger != null) {
            nextLogger.log(message, level);
        }
    }

    protected abstract void write(String message);
}

