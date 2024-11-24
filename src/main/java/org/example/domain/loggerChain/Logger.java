package org.example.domain.loggerChain;

public interface Logger {
    void setNext(Logger nextLogger);
    void log(String message, LogLevel level);
}

