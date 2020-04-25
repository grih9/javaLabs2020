package ru.spbstu.lab4.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.spbstu.lab4.gui.controllers.ChooseWindowController;
import ru.spbstu.lab4.gui.controllers.MainWindowController;
import ru.spbstu.lab4.gui.controllers.StartWindowController;
import ru.spbstu.lab4.repository.ProductDB;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class GUIMain extends Application {
    private Stage window;
    private ProductDB productDB;
    private Connection connection;

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
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        try {
            Class.forName(DB_DRIVER);
            System.out.println("Соединяемся с БД...");
            connection = getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("JDBC драйвер для СУБД не найден!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка SQL!");
        }

        window = primaryStage;
        showChooseWindow();
        window.show();

    }

    public void showChooseWindow() {
        try {
            final FXMLLoader loader = new FXMLLoader(getClass().getResource("/ChooseWindow.fxml"));
            window.setScene(new Scene(loader.load()));
            window.setTitle("Выбор режима");
            window.setResizable(false);

            final ChooseWindowController controller = loader.getController();
            controller.setGUI(this);
            controller.setConnection(connection);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showStartWindow() {
        try {
            final FXMLLoader loader = new FXMLLoader(getClass().getResource("/StartWindow.fxml"));
            window.setScene(new Scene(loader.load()));
            window.setTitle("Начальная генерация");

            final StartWindowController controller = loader.getController();
            controller.setGUI(this);
            controller.setProductDB(productDB);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showMainWindow() {
        try {
            final FXMLLoader loader = new FXMLLoader(getClass().getResource("/MainWindow.fxml"));
            window.setScene(new Scene(loader.load()));
            window.setTitle("Продукты");

            final MainWindowController controller = loader.getController();
            controller.setGUI(this);
            controller.setProductDB(productDB);
            controller.setConnection(connection);
            controller.updateTable();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void showOK(String message) {
        final Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("OK");
        alert.initOwner(window);
        alert.setHeaderText("Выполнено успешно");
        alert.setContentText(message);
        alert.initModality(Modality.APPLICATION_MODAL);

        alert.showAndWait();
    }

    public void showError(String errorMessage) {
        final Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.initOwner(window);
        alert.setHeaderText("Возникла ошибка");
        alert.setContentText(errorMessage);
        alert.initModality(Modality.APPLICATION_MODAL);

        alert.showAndWait();
    }

    public void setProductBD(ProductDB productDB) {
        this.productDB = productDB;
    }

    public void close() {
        window.close();
    }
}
