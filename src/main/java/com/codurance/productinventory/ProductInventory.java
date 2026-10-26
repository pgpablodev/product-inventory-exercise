package com.codurance.productinventory;

import java.util.HashMap;
import java.util.Map;

public class ProductInventory {
    private final Map<String, Integer> inventory = new HashMap<>();

    public void addStock(String productId, int quantity) {
        if(quantity < 0){
            throw new IllegalArgumentException("Quantity cannot be negative");
        }

        inventory.put(productId, checkStock(productId) + quantity);
    }

    public void removeStock(String productId, int quantity) {
        if(quantity < 0){
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        
        int currentStock = checkStock(productId);
        if (currentStock < quantity) {
            throw new IllegalArgumentException("Not enough stock available for " + productId);
        }
        inventory.put(productId, currentStock - quantity);
    }

    public int checkStock(String productId) {
        return inventory.getOrDefault(productId, 0);
    }
}