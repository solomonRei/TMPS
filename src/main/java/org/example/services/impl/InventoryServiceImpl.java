package org.example.services.impl;

import org.example.domain.loggerChain.LogLevel;
import org.example.domain.loggerChain.Logger;
import org.example.domain.loggerChain.LoggerChain;
import org.example.services.InventoryService;

public class InventoryServiceImpl implements InventoryService {
    private final Logger logger = LoggerChain.getLoggerChain();

    @Override
    public boolean checkStock(Long productId) {
        logger.log("Checking stock for product " + productId, LogLevel.INFO);
        return true;
    }
}
