package ru.spbstu.lab4;
import java.sql.*;

import org.h2.tools.DeleteDbFiles;
import ru.spbstu.lab4.repository.*;

public class Warehouse {
    public static final String DB_DIR = "c:/javalabs/lab4/db/";
    public static final String DB_FILE = "warehouse";
    public static final String DB_URL = "jdbc:h2:/" + DB_DIR + "/" + DB_FILE;
    public static final String DB_DRIVER = "org.h2.Driver";

    Product product;


    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public Warehouse(boolean renew) throws SQLException, ClassNotFoundException {
        if (renew) {
            DeleteDbFiles.execute(DB_DIR, DB_FILE, false);
        }
        Class.forName(DB_DRIVER);
        product = new Product();
    }

    public Warehouse() throws SQLException, ClassNotFoundException {
        this(false);
    }

    public void createTablesAndForeignKeys() throws SQLException {
        product.createTable();
    }

    public static void main(String[] args) {
        try {
            //Class.forName(DB_DRIVER);
            //Connection connection = DriverManager.getConnection(DB_URL);
            Warehouse warehouse = new Warehouse();
            warehouse.createTablesAndForeignKeys();
            System.out.println("Соединение с СУБД установлено.");
            //connection.close();
            //System.out.println("Отключение от СУБД выполнено.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("JDBC драйвер для СУБД не найден!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка SQL!");
        }

    }
}
