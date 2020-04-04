package ru.spbstu.lab4.repository;

import java.sql.SQLException;

public class Product extends BaseTable implements TableOperations {
    public Product() throws SQLException {
        super("product");
    }

    @Override
    public void createTable() throws SQLException {
        super.executeSQLStatement("CREATE TABLE IF NOT EXISTS product(" +
                "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                "progid BIGINT NOT NULL," +
                "name VARCHAR(255) NOT NULL," +
                "cost BIGINT NOT NULL)", "Создана таблица " + tableName);
    }

    @Override
    public void createExtraConstraints() throws SQLException {
        super.executeSQLStatement(
                "ALTER TABLE product ADD CONSTRAINT cost CHECK(cost <= 100 and cost > 0)",
                "Cоздано ограничение для shares, поле delta = [1,100]");
        super.executeSQLStatement(
                " ALTER TABLE product ADD CONSTRAINT progid " +
                        "CHECK(progId <= 100 and progId > 0)",
                "Cоздано ограничение для shares, поле changeProbability = 1..100");

    }
}
