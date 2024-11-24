package org.example.domain.observer;

import org.example.domain.models.Order;

import java.util.ArrayList;
import java.util.List;

public class ObservableOrderService {
    private final List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(String eventType, Order order) {
        for (Observer observer : observers) {
            observer.update(eventType, order);
        }
    }
}

