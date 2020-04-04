package ru.spbstu.lab4.model;

import java.util.Objects;

public class Product {
    private static long n = 0;
    private long id;
    private long prodId;
    private String title;
    private long cost;

    public Product() {
        this(n + 1, n + 1, "товар" + (n + 1), 10);
        ++n;
    }

    public Product(long id, long prodId, String title, long cost) {
        this.id = id;
        this.prodId = prodId;
        this.title = title;
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
        return id == product.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProdId() {
        return prodId;
    }

    public void setProdId(long prodId) {
        this.prodId = prodId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }
}
