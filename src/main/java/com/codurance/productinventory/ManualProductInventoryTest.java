package com.codurance.productinventory;

public class ManualProductInventoryTest{
    public static void main(String[] args){
        testAddStock();
        testIncreaseExistingStock();
        testCheckStockOfUnknownProduct();
        testRemoveAvailableStock();
        testRemoveMoreStockThanAvailable();
        testRemoveAllAvailableStock();
        testIndependentStockForDifferentProducts();
        testAddZeroStock();
        testAddNegativeStock();
        testRemoveNegativeStock();
        testRemoveStockFromUnknownProduct();
    }

    public static void testAddStock(){
        ProductInventory inventory = new ProductInventory();
        inventory.addStock("P001", 10);

        check("testAddStock", inventory.checkStock("P001") == 10);
    }

    public static void testIncreaseExistingStock(){
        ProductInventory inventory = new ProductInventory();
        inventory.addStock("P001", 10);
        inventory.addStock("P001", 5);

        check("testIncreaseExistingStock", inventory.checkStock("P001") == 15);
    }

    public static void testCheckStockOfUnknownProduct(){
        ProductInventory inventory = new ProductInventory();

        check("testCheckStockOfUnknownProduct", inventory.checkStock("P999") == 0);
    }

    public static void testRemoveAvailableStock(){
        ProductInventory inventory = new ProductInventory();
        inventory.addStock("P001", 10);
        inventory.removeStock("P001", 4);

        check("testRemoveAvailableStock", inventory.checkStock("P001") == 6);
    }

    public static void testRemoveMoreStockThanAvailable(){
        ProductInventory inventory = new ProductInventory();
        inventory.addStock("P001", 5);

        boolean exceptionThrown = false;
        try {
            inventory.removeStock("P001", 10);
        } catch (IllegalArgumentException exception){
            exceptionThrown = true;
        }

        check(
            "testRemoveMoreStockThanAvailable",
            exceptionThrown && inventory.checkStock("P001") == 5
        );
    }

    public static void testRemoveAllAvailableStock(){
        ProductInventory inventory = new ProductInventory();
        inventory.addStock("P001", 5);
        inventory.removeStock("P001", 5);

        check("testRemoveAllAvailableStock", inventory.checkStock("P001") == 0);
    }

    public static void testIndependentStockForDifferentProducts(){
        ProductInventory inventory = new ProductInventory();
        inventory.addStock("P001", 10);
        inventory.addStock("P002", 20);
        inventory.removeStock("P001", 3);

        check(
            "testIndependentStockForDifferentProducts",
            inventory.checkStock("P001") == 7
                && inventory.checkStock("P002") == 20
        );
    }

    public static void testAddZeroStock(){
        ProductInventory inventory = new ProductInventory();
        inventory.addStock("P001", 10);
        inventory.addStock("P001", 0);

        check("testAddZeroStock", inventory.checkStock("P001") == 10);
    }

    public static void testAddNegativeStock(){
        ProductInventory inventory = new ProductInventory();

        boolean exceptionThrown = false;
        try {
            inventory.addStock("P001", -5);
        } catch (IllegalArgumentException exception){
            exceptionThrown = true;
        }

        check("testAddNegativeStock", exceptionThrown);
    }

    public static void testRemoveNegativeStock(){
        ProductInventory inventory = new ProductInventory();
        inventory.addStock("P001", 10);

        boolean exceptionThrown = false;
        try {
            inventory.removeStock("P001", -3);
        } catch (IllegalArgumentException exception){
            exceptionThrown = true;
        }

        check(
            "testRemoveNegativeStock",
            exceptionThrown && inventory.checkStock("P001") == 10
        );
    }

    public static void testRemoveStockFromUnknownProduct(){
        ProductInventory inventory = new ProductInventory();

        boolean exceptionThrown = false;
        try {
            inventory.removeStock("P999", 1);
        } catch (IllegalArgumentException exception){
            exceptionThrown = true;
        }

        check("testRemoveStockFromUnknownProduct", exceptionThrown);
    }

    private static void check(String testName, boolean condition){
        if (condition){
            System.out.println("PASS: " + testName);
        } else {
            System.out.println("FAIL: " + testName);
        }
    }
}
