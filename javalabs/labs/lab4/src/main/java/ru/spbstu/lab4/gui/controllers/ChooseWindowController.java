package ru.spbstu.lab4.gui.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.RadioButton;
import ru.spbstu.lab4.gui.GUIMain;

public class ChooseWindowController {
    GUIMain gui;
    public RadioButton console;
    public RadioButton graphic;

    public void startButtonClicked(ActionEvent actionEvent) {
        if (console.isSelected()) {

        } else {

        }
    }

    public void setGUI(GUIMain gui) {
        this.gui = gui;
    }

}
