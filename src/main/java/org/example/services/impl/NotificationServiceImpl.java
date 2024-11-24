package org.example.services.impl;

import org.example.domain.loggerChain.LogLevel;
import org.example.domain.loggerChain.Logger;
import org.example.domain.loggerChain.LoggerChain;
import org.example.services.NotificationService;

public class NotificationServiceImpl implements NotificationService {
    private final Logger logger = LoggerChain.getLoggerChain();

    @Override
    public void sendNotification(String message) {
        logger.log("Sending notification: " + message, LogLevel.ERROR);
    }
}
