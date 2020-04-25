package ru.spbstu.lab4.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import ru.spbstu.lab4.gui.GUIMain;
import ru.spbstu.lab4.repository.ProductDB;
import ru.spbstu.lab4.service.DBService;

import java.sql.Connection;
import java.sql.SQLException;

public class ChooseWindowController {
    @FXML
    private RadioButton graphic;
    @FXML
    private RadioButton console;

    private GUIMain gui;
    private Connection connection;

    @FXML
    private void startButtonClicked(ActionEvent actionEvent) {
        if (console.isSelected()) {
           try {
               gui.close();
               DBService dbService = new DBService(System.in, System.out, connection);
               dbService.start();
               connection.close();
               System.out.println("Отключение от СУБД выполнено.");
           } catch (SQLException e) {
               e.printStackTrace();
           }
        } else {
            gui.setProductBD(new ProductDB(connection));
            gui.showStartWindow();
        }
    }

    public void setGUI(GUIMain gui) {
        this.gui = gui;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

}
