package com.classy.class_2021a_and_9;

import java.util.ArrayList;

public class Sushi {

    private String key = "";
    private String name = "";
    private int numOfPieces = 0;
    private double price = 0.0;
    private boolean vegan = false;
    private ArrayList<String> ingredients = new ArrayList<>();

    public Sushi() { }

    public String getKey() {
        return key;
    }

    public Sushi setKey(String key) {
        this.key = key;
        return this;
    }

    public String getName() {
        return name;
    }

    public Sushi setName(String name) {
        this.name = name;
        return this;
    }

    public int getNumOfPieces() {
        return numOfPieces;
    }

    public Sushi setNumOfPieces(int numOfPieces) {
        this.numOfPieces = numOfPieces;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public Sushi setPrice(double price) {
        this.price = price;
        return this;
    }

    public boolean isVegan() {
        return vegan;
    }

    public Sushi setVegan(boolean vegan) {
        this.vegan = vegan;
        return this;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public Sushi setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
        return this;
    }
}
