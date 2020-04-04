package ru.spbstu.lab4.repository;

import java.sql.SQLException;

public interface TableOperations {
    void createTable() throws SQLException;
    void createExtraConstraints() throws SQLException;
}
