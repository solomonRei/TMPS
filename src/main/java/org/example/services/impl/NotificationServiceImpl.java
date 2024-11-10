package org.example.services.impl;

import org.example.services.NotificationService;

public class NotificationServiceImpl implements NotificationService {
    @Override
    public void sendNotification(String message) {
        System.out.println("Sending notification: " + message);
    }
}
