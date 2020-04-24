package ru.spbstu.lab4.gui.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import ru.spbstu.lab4.gui.GUIMain;

public class StartWindowController {

    GUIMain gui;
    public TextField numGenerate;

    public void AcceptButtonClicked(ActionEvent actionEvent) {
        try {
            int number = Integer.parseInt(numGenerate.getText());
            showOK("Успешно сгенерировано");
        } catch (NumberFormatException e) {
            gui.showError("Неверный формат данных. Введите число")
        }
    }

    public void setGUI(GUIMain gui) {
        this.gui = gui;
    }
}
