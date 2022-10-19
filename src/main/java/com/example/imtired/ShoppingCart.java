package com.example.imtired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart {
    private static ShoppingCart INSTANCE;

    public static ShoppingCart getInstance(){
        if(INSTANCE == null){
            INSTANCE = new ShoppingCart();
        }
        return INSTANCE;
    }

    private Map<String, CartEntry> entries;

    private ShoppingCart(){
        this.entries = new HashMap<>();
    }

    public void addProduct(String productName){
        CartEntry productEntry = entries.get(productName.toUpperCase());
        if(productEntry!=null){
            productEntry.increaseQuantity();
        } else {
            Product product = Product.valueOf(productName);
            CartEntry entry = new CartEntry(product, 1);
            entries.put(productName.toUpperCase(), entry);
        }
    }

    public void removeProduct(String productName){
        CartEntry productEntry = entries.get(productName.toUpperCase());
        if(productEntry!=null){
            productEntry.decreaseQuantity();
        }
    }

    public String getQuantity(String productName){
        CartEntry productEntry = entries.get(productName.toUpperCase());
        if(productEntry!=null){
            return String.valueOf(productEntry.getQuantity());
        }
        return null;
    }

    public int calculateCost(){
        int total = 0;
        for(CartEntry productEntry: entries.values()){
            int entryCost = productEntry.getProduct().getPrice()*productEntry.getQuantity();
            total += entryCost;
        }
        return total;
    }

    public List<CartEntry> getEntries(){
        return new ArrayList<>(entries.values());
    }

}
