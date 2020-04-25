package ru.spbstu.lab4.model;

import java.util.Objects;

public class Product {
    private int id;
    private String prodid;
    private String title;
    private double cost;

    public Product(int id, String prodid, String title, double cost) {
        if (cost < 0) {
            throw new IllegalArgumentException("Цена не может быть отрицательной.");
        }

        this.id = id;
        this.prodid = prodid;
        this.title = title;
        this.cost = cost;
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

    public double getCost() {
        return cost;
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
                prodid.equals(product.prodid) &&
                title.equals(product.title) &&
                cost == product.cost;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, prodid, title, cost);
    }

    @Override
    public String toString() {
        return "id : " + id + ", prodid : " + prodid +
                ", title : " + title + ", cost : " + cost;
    }

}
