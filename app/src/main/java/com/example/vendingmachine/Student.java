package com.example.vendingmachine;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import product.IProduct;

public class Student {
    private int money;
    private int number;

    Student (int number, int money){
        this.number = number;
        this.money = money;
    }

    public int getNumber(){
        return this.number;
    }

    public int[] choose(ArrayList<IProduct> products) {
        int[] list = new int[3];
        Random rand = new Random();
        do {
            list[0] = (int) (rand.nextInt(products.size()));
            do {
                list[1] = (int) (rand.nextInt(products.size()));
            }while (list[1] == list[0]);
            do {
                list[2] = (int) (rand.nextInt(products.size()));
            }while ((list[2] == list[0]) || (list[2] == list[1]));
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }while(products.get(list[0]).getCost() +  products.get(list[1]).getCost() + products.get(list[2]).getCost() > money);
        return list;
    }

    public void pay(int sum) {
        money -= sum;
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}