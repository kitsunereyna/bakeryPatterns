package com.example.imtired;

public enum Product {
    TIRAMISU("TIRAMISU", "tir.jpg", 400),
    NAPOLEON("NAPOLEON", "tir.jpg", 500),
    CHEESECAKE("CHEESECAKE", "tir.jpg", 480);
    private String description;
    private String imageFile;
    private int price;

    Product(String description, String image, int price){
        this.description = description;
        this.price = price;
    }

    public String getDescription(){
        return description;
    };

    public String getImageFile(){
        return imageFile;
    };

    public int getPrice(){
        return this.price;
    };


}
