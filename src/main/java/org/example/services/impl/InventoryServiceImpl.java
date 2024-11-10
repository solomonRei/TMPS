package org.example.services.impl;

import org.example.services.InventoryService;

public class InventoryServiceImpl implements InventoryService {
    @Override
    public boolean checkStock(Long productId) {
        System.out.println("Checking stock for product " + productId);
        return true;
    }
}
