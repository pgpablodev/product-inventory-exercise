package com.codurance.productinventory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ProductInventoryTest{
    @Test
    public void shouldIncreaseStockWhenAddingProduct() {
        ProductInventory inventory = new ProductInventory();
        inventory.addStock("P001", 10);
        assertEquals(10, inventory.checkStock("P001"));
    }

    @Test
    public void shouldIncreaseExistingStockWhenAddingMoreStock() {
        ProductInventory inventory = new ProductInventory();

        inventory.addStock("P001", 10);
        inventory.addStock("P001", 5);

        assertEquals(15, inventory.checkStock("P001"));
    }

    @Test
    public void shouldReturnZeroWhenCheckingStockOfUnknownProduct() {
        ProductInventory inventory = new ProductInventory();
        assertEquals(0, inventory.checkStock("P999"));
    }

    @Test
    public void shouldDecreaseStockWhenRemovingAvailableStock() {
        ProductInventory inventory = new ProductInventory();
        inventory.addStock("P001", 10);
        inventory.removeStock("P001", 4);
        assertEquals(6, inventory.checkStock("P001"));
    }

    @Test
    public void shouldThrowExceptionWhenRemovingMoreStockThanAvailable() {
        ProductInventory inventory = new ProductInventory();

        inventory.addStock("P001", 5);

        assertThrows(
            IllegalArgumentException.class,
            () -> inventory.removeStock("P001", 10)
        );

        assertEquals(5, inventory.checkStock("P001"));
    }

    @Test
    public void shouldMaintainIndependentStockForDifferentProducts() {
        ProductInventory inventory = new ProductInventory();

        inventory.addStock("P001", 10);
        inventory.addStock("P002", 20);

        inventory.removeStock("P001", 3);

        assertEquals(7, inventory.checkStock("P001"));
        assertEquals(20, inventory.checkStock("P002"));
    }

    @Test
    public void shouldKeepStockUnchangedWhenAddingZeroStock() {
        ProductInventory inventory = new ProductInventory();

        inventory.addStock("P001", 10);
        inventory.addStock("P001", 0);

        assertEquals(10, inventory.checkStock("P001"));
    }

    @Test
    public void shouldThrowExceptionWhenAddingNegativeStock() {
        ProductInventory inventory = new ProductInventory();

        assertThrows(
            IllegalArgumentException.class,
            () -> inventory.addStock("P001", -5)
        );
    }

    @Test
    public void shouldThrowExceptionWhenRemovingNegativeStock() {
        ProductInventory inventory = new ProductInventory();
        inventory.addStock("P001", 10);

        assertThrows(
            IllegalArgumentException.class,
            () -> inventory.removeStock("P001", -3)
        );
    }

    @Test
    public void shouldThrowExceptionWhenRemovingStockFromUnknownProduct() {
        ProductInventory inventory = new ProductInventory();

        assertThrows(
            IllegalArgumentException.class,
            () -> inventory.removeStock("P999", 1)
        );
    }
}