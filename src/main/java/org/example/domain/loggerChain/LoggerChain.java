package org.example.domain.loggerChain;

public class LoggerChain {
    public static Logger getLoggerChain() {
        Logger errorLogger = new ErrorLogger();
        Logger consoleLogger = new InfoLogger();
        Logger fileLogger = new FileLogger(LogLevel.DEBUG);

        consoleLogger.setNext(fileLogger);
        fileLogger.setNext(errorLogger);

        return consoleLogger;
    }
}

