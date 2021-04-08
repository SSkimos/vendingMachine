package com.example.vendingmachine;

import java.util.ArrayList;

import IFabrics.IFabric;
import product.IProduct;

public class Courier {
    private Courier instance = null;

    private Courier() {

    }

    public Courier getInstance() {
        if (instance == null)
            instance = new Courier();
        return instance;
    }

    public void addProduct (IFabric fabric, Machine machine, int amount) {
        ArrayList<IProduct> newProduct = new ArrayList <IProduct>();
        for (int i = 0; i < amount; i++) {
            newProduct.add(fabric.create());
        }
        machine.addProduct(newProduct);
    }
}