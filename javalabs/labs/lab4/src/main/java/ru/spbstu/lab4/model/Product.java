package ru.spbstu.lab4.model;

import java.util.Objects;

public class Product {
    private int id;
    private String prodId;
    private String title;
    private double cost;

//    public Product() {
//        this(n + 1, n + 1, "товар" + (n + 1), 10);
//        ++n;
//    }

    public Product(int id, String prodId, String title, double cost) {
        this.id = id;
        this.prodId = prodId;
        this.title = title;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public String getProdId() {
        return prodId;
    }

    public String getTitle() {
        return title;
    }

    public double getCost() {
        return cost;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCost(double cost) {
        this.cost = cost;
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
                prodId.equals(product.prodId) &&
                title.equals(product.title) &&
                cost == product.cost;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, prodId, title, cost);
    }

    @Override
    public String toString() {
        return "id : " + id + ", prodid : " + prodId +
                ", title : " + title + ", cost : " + cost;
    }

}
