package ru.spbstu.lab4.repository;

import ru.spbstu.lab4.model.Product;

import java.sql.*;

public class ProductDB {
    private final String TABLE_NAME = "product";

    private final Connection connection;

    public ProductDB(Connection connection) {
        this.connection = connection;

        try {
            if (!tableExists()) {
                createTable();
            } else {
                deleteTable();
                createTable();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Не удалось установить соединение", e);
        }
    }

    public void add(Product product) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO " + TABLE_NAME + " VALUES (?, ?, ?)")) {
            statement.setString(1, product.getProdId());
            statement.setString(2, product.getTitle());
            statement.setDouble(3, product.getCost());

            if (!statement.execute()) {
                throw new SQLException();
            }
        } catch (SQLException e) {
            throw new RuntimeException("SQL ошибка! Не удалось добавить товар", e);
        }
    }

    public void removeByTitle(String title) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM " + TABLE_NAME + " WHERE title = ?")) {
            statement.setString(1, title);

            if (statement.executeUpdate() == 0) {
                throw new RuntimeException("Ошибка удаления. Такого товара нет в таблице.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("SQL ошибка! Не удалось удалить товар", e);
        }
    }

    public ResultSet getTable() {
        try (Statement statement = connection.createStatement()) {
            ResultSet res = statement.executeQuery("SELECT * FROM " + TABLE_NAME);
            while (res.next()) {
                int id = res.getInt("id");
                String prodid = res.getString("prodid");
                String title = res.getString("title");
                double cost = res.getDouble("cost");
                System.out.println(id + " : " + prodid + " : " + title + " : " + cost);
            }
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public ResultSet getFilteredTable(double from, double to) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE cost >= ? and cost <= ?")) {
            statement.setDouble(1, from);
            statement.setDouble(2, to);
            ResultSet rs = statement.executeQuery();
            rs.next();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void changeCost(String title, double cost) {
        try (PreparedStatement statement = connection.prepareStatement("UPDATE " + TABLE_NAME + " SET cost = ? WHERE title = ?")) {
            statement.setString(2, title);
            statement.setDouble(1, cost);
            if (statement.executeUpdate() == 0) {
                throw new SQLException();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Такого товара нет");
        }
    }

    public double getCost(String title) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT cost FROM " + TABLE_NAME + " WHERE title = ?")) {
            statement.setString(1, title);
            ResultSet rs = statement.executeQuery();
            rs.next();
            return rs.getDouble("cost");
        } catch (SQLException e) {
            System.out.println("Такого товара нет");
            return 0;
        }
    }


    private boolean tableExists() throws SQLException {
        DatabaseMetaData meta = connection.getMetaData();
        ResultSet res = meta.getTables(null, null, TABLE_NAME, new String[]{"TABLE"});
        while (res.next()) {
            if (res.getString("TABLE_NAME").equals(TABLE_NAME)) {
                return true;
            }
        }
        return false;
    }


    private void createTable() {
        try (Statement statement = connection.createStatement()) {
            if (!statement.execute("CREATE TABLE " + TABLE_NAME +
                    "(id INT NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                    "prodid VARCHAR(50)," +
                    "title VARCHAR(100)," +
                    "cost FLOAT NOT NULL);")) {
                throw new SQLException();
            }
        } catch (SQLException e) {
            throw new RuntimeException("SQL ошибка! Не удалось создать таблицу", e);
        }
    }

    public void deleteTable() {
        try (Statement statement = connection.createStatement()) {
            if(!statement.execute("DROP TABLE " + TABLE_NAME)) {
                throw new SQLException();
            }
        } catch (SQLException e) {
            throw new RuntimeException("SQL ошибка! Не удалось удалить таблицу", e);
        }

    }

//    public void clear(Connection connection) {
//        try (Statement statement = connection.createStatement()) {
//            statement.exe
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }
}
