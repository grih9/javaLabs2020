package ru.spbstu.lab4.model;

import java.util.Objects;

public class Product {
    private int id;
    private String prodid;
    private String title;
    private double price;

    public Product(int id, String prodid, String title, double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Цена не может быть отрицательной.");
        }

        this.id = id;
        this.prodid = prodid;
        this.title = title;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getProdid() {
        return prodid;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProdId(String prodId) {
        this.prodid = prodid;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Product product = (Product) o;
        return id == product.id &&
                prodid.equals(product.prodid) &&
                title.equals(product.title) &&
                price == product.price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, prodid, title, price);
    }

    @Override
    public String toString() {
        return id + " : " + prodid +
                ": " + title + ": " + price;
    }

}
