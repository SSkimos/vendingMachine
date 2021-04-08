package com.example.vendingmachine;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import IFabrics.IFabric;
import product.IProduct;

public class Machine {
    private String name;
    private ArrayList<IProduct> products;
    private Status status;
    private Student client;
    private int clientNumber;
    private int haveToPay;
    private int[] choose;
    private long cash;
    private ArrayList<Student> queue;

    Machine(String name) {
        this.name = name;
        products = new ArrayList<IProduct>();
        status = Status.DOINGNOTHING;
        cash = 0;
        haveToPay = 0;
        clientNumber = 0;
    }

    public void addProduct(ArrayList<IProduct> newProducts) {
        if (status == status.DOINGNOTHING)
            products.addAll(newProducts);
    }

    public void addProduct(IFabric fabric, int amount) {
        ArrayList<IProduct> newProduct = new ArrayList<IProduct>();
        for (int i = 0; i < amount; i++) {
            newProduct.add(fabric.create());
        }
        addProduct(newProduct);
    }

    public void startToWork(Student client) {
        this.client = client;
        this.clientNumber = client.getNumber();
        status = Status.ISWORKINGWITHACLIENT;
    }

    public void workingWithAClient() {
        choose = client.choose(products);
        haveToPay = 0;
        for (int i = 0; i < 3; i++) {
            haveToPay += products.get(choose[i]).getCost();
        }
        status = status.ISPAYING;
    }

    public void paying() {
        client.pay(haveToPay);
        cash += haveToPay;
        haveToPay = 0;
        status = status.ISDELIVERING;
    }

    public void delivering() {
        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 2-i; k++) {
                if (choose[k] < choose[k+1]) {
                    int tmp = choose[k];
                    choose[k] = choose[k+1];
                    choose[k+1] = tmp;
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            products.remove(choose[i]);
        }
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        haveToPay = 0;
        clientNumber = 0;
        status = status.DOINGNOTHING;
        choose = new int[3];
    }

    public void setQueue(ArrayList<Student> queue) {
        this.queue = queue;
    }

    public ArrayList<Student> getQueue(){
        return queue;
    }

    public String getName() {
        return name;
    }

    public Status getStatus() {
        return status;
    }

    public int getClientNumber() {
        return clientNumber;
    }

    public int[] getChoose() {
        return choose;
    }

    public ArrayList<IProduct> getProduct() {
        return products;
    }
}

enum Status {
    ISWORKINGWITHACLIENT,
    DOINGNOTHING,
    ISPAYING,
    ISDELIVERING
}
