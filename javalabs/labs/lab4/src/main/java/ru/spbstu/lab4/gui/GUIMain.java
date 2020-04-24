package ru.spbstu.lab4.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.spbstu.lab4.gui.controllers.ChooseWindow;
import ru.spbstu.lab4.gui.controllers.ChooseWindowController;
import ru.spbstu.lab4.gui.controllers.StartWindowController;
import ru.spbstu.lab4.model.Product;
import ru.spbstu.lab4.repository.ProductDB;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.EventListener;


public class GUIMain extends Application {
    Stage window;
    ProductDB productDB;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        window = primaryStage;
        showChooseWindow();
        window.show();

//        Label label1 = new Label("1st scene");
//        Button button1 = new Button("Start");
//
//        button1.setOnAction(c -> window.setScene(scene2));
//
//        VBox layout1 = new VBox(20);
//        layout1.getChildren().addAll(label1, button1);
//
//        scene1 = new Scene(layout1, 200, 200);
//
//        Label label2 = new Label("2nd scene");
//        Button button2 = new Button("END");
//
//        button2.setOnAction(c -> window.setScene(scene1));
//
//        StackPane layout2 = new StackPane();
//        layout2.getChildren().addAll(label2, button2);
//
//        scene2 = new Scene(layout2, 200, 200);
//
//        window.setScene(scene1);
//        window.setTitle("Warehouse");
//        window.show();
    }

    private void showChooseWindow() {
        try {
            final FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ChooseWindow.fxml"));
            window.setScene(new Scene(loader.load()));
            window.setTitle("Выбор режима");

            final ChooseWindowController controller = loader.getController();
            controller.setGUI(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showStartWindow() {
        try {
            final FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/StartWindow.fxml"));
            window.setScene(new Scene(loader.load()));
            window.setTitle("Начальная генерация");

            final StartWindowController controller = loader.getController();
            controller.setGUI(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showMainWindow() {
        try {
            final FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainWindow.fxml"));
            window.setScene(new Scene(loader.load()));
            window.setTitle("Выбор режима");

            final ChooseWindowController controller = loader.getController();
            controller.setGUI(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void showOK(String message) {
        final Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("OK");
        alert.initOwner(window);
        alert.setHeaderText("Выполнено успешно");
        alert.setContentText(message);
        alert.initModality(Modality.WINDOW_MODAL);

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
}
