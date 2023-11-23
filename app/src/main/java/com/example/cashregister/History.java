package com.example.cashregister;

import java.io.Serializable;
import java.util.Date;

public class History implements Serializable {
    String productName;
    double totalPrice;
    int quantity;
    String date;

    public History(String productName, double totalPrice, int quantity, String date) {
        this.productName = productName;
        this.totalPrice = totalPrice;
        this.quantity = quantity;
        this.date = date;
    }

    public String getProductName() {
        return productName;
    }

    public double getProductPrice() {
        return totalPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getDate() {
        return date;
    }
}
