package ru.spbstu.lab4.repository;

import ru.spbstu.lab4.Warehouse;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseTable implements Closeable {
    Connection connection;      //JDBS-connection for working with the table
    String tableName;           //Name of the table

    BaseTable(String tableName) throws SQLException {
        this.tableName = tableName;
        this.connection = Warehouse.getConnection();
    }

    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Ошибка закрытия SQL соединения");
        }
    }

    void executeSQLStatement(String sql, String description) throws SQLException {
        reopenConnection();
        Statement statement = connection.createStatement();
        statement.execute(sql);
        statement.close();
        if (description != null) {
            System.out.println(description);
        }
    }

    void executeSQLStatement(String sql) throws SQLException {
        executeSQLStatement(sql, null);
    }

    void reopenConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = Warehouse.getConnection();
        }
    }
}
