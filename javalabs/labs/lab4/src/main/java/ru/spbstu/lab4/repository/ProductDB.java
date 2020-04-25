package ru.spbstu.lab4.repository;

import ru.spbstu.lab4.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ProductDB {
    private final Connection connection;

    public ProductDB(Connection connection) {
        this.connection = connection;
        deleteTable();
        createTable();
    }

    public void add(Product product) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO products (prodid, title, price) VALUES (?, ?, ?)")) {
            statement.setString(1, product.getProdid());
            statement.setString(2, product.getTitle());
            statement.setDouble(3, product.getPrice());

            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("SQL ошибка! Не удалось добавить товар", e);
        }
    }

    public void removeByTitle(String title) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM products WHERE title = ?")) {
            statement.setString(1, title);

            if (statement.executeUpdate() == 0) {
                throw new NoSuchElementException("Ошибка удаления. Такого товара нет в таблице.");
            }

        } catch (SQLException e) {
            throw new RuntimeException("SQL ошибка! Не удалось удалить товар", e);
        }
    }

    public List<Product> getTableList() {
        List<Product> list = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet res = statement.executeQuery("SELECT * FROM products");
            while (res.next()) {
                int id = res.getInt("id");
                String prodid = res.getString("prodid");
                String title = res.getString("title");
                double price = res.getDouble("price");
                list.add(new Product(id, prodid, title, price));
            }
        } catch (SQLException e) {
            throw new RuntimeException("SQL ошибка! Не удалось получить таблицу", e);
        }

        return list;
    }

    public List<Product> getFilteredTableList(double from, double to) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM products WHERE price >= ? and price <= ?")) {
            List<Product> products = new ArrayList<>();
            statement.setDouble(1, from);
            statement.setDouble(2, to);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String prodid = rs.getString("prodid");
                String title = rs.getString("title");
                double price = rs.getDouble("price");
                products.add(new Product(id, prodid, title, price));
            }
            return products;
        } catch (SQLException e) {
            throw new RuntimeException("SQL ошибка! Не удалось получить отфильтрованную таблицу", e);
        }
    }

    public void changePrice(String title, double price) {
        try (PreparedStatement statement = connection.prepareStatement("UPDATE products SET price = ? WHERE title = ?")) {
            statement.setString(2, title);
            statement.setDouble(1, price);
            if (statement.executeUpdate() == 0) {
                throw new NoSuchElementException("Ошибка изменения. Такого товара нет в таблице.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("SQL ошибка! Не удалось поменять цену", e);
        }
    }

    public Product findProduct(String title) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM products WHERE title = ?")) {
            statement.setString(1, title);
            ResultSet rs = statement.executeQuery();

            if (rs.getRow() == 0) {
                throw new NoSuchElementException("Ошибка поиска. Такого товара нет в таблице.");
            }

            rs.next();
            return new Product(rs.getInt("id"), rs.getString("prodid"),
                        rs.getString("title"), rs.getDouble("price"));
        } catch (SQLException e) {
            throw new RuntimeException("SQL ошибка! Не удалось найти товар", e);
        }
    }

    private void createTable() {
        try (Statement statement = connection.createStatement()) {
           statement.execute("CREATE TABLE IF NOT EXISTS products(" +
                    "id INT NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                    "prodid VARCHAR(50) NOT NULL UNIQUE," +
                    "title VARCHAR(100) NOT NULL UNIQUE," +
                    "price DOUBLE NOT NULL);");
        } catch (SQLException e) {
            throw new RuntimeException("SQL ошибка! Не удалось создать таблицу", e);
        }
    }

    public void deleteTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS products");
        } catch (SQLException e) {
            throw new RuntimeException("SQL ошибка! Не удалось удалить таблицу", e);
        }

    }
}
