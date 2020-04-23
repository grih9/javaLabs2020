package ru.spbstu.lab4;

import ru.spbstu.lab4.service.DBService;

import java.sql.*;

public class Warehouse {
    public static final String DB_DIR = "c:/javalabs/lab4/db/";
    public static final String DB_FILE = "warehouse";
    public static final String DB_URL = "jdbc:h2:" + DB_DIR + "/" + DB_FILE;
    public static final String DB_DRIVER = "org.h2.Driver";

    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL);

        if (connection.isValid(1)) {
            System.out.println("Соединение с СУБД установлено.");
        }

        return connection;
    }

    public static void main(String[] args) {
        try {
            Class.forName(DB_DRIVER);
            System.out.println("Соединяемся с БД...");
            Connection connection = getConnection();
            DBService dbService = new DBService(System.in, System.out, connection);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM products");
            while (rs.next()) {
                System.out.println((rs.getInt("id") + " : " +
                        rs.getString("prodid") + " : " +
                        rs.getString("title") + " : " +
                        rs.getDouble("cost") + " : "));
            }
            connection.close();
            System.out.println("Отключение от СУБД выполнено.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("JDBC драйвер для СУБД не найден!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка SQL!");
        }

    }
}
