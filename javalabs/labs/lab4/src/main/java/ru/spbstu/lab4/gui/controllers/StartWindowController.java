package ru.spbstu.lab4.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import ru.spbstu.lab4.gui.GUIMain;

public class StartWindowController {

    private GUIMain gui;

    @FXML
    private TextField numGenerate;

    @FXML
    private void AcceptButtonClicked(ActionEvent actionEvent) {
        try {
            int number = Integer.parseInt(numGenerate.getText());
            gui.showOK("Успешно сгенерировано");
            gui.showMainWindow();
        } catch (NumberFormatException e) {
            gui.showError("Неверный формат данных. Введите число");
        }
    }

    public void setGUI(GUIMain gui) {
        this.gui = gui;
    }
}
