package ru.spbstu.lab4.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import ru.spbstu.lab4.gui.GUIMain;
import ru.spbstu.lab4.model.Product;
import ru.spbstu.lab4.repository.ProductDB;

import java.util.UUID;

public class StartWindowController {

    private GUIMain gui;
    private ProductDB productDB;

    @FXML
    private TextField numGenerate;

    @FXML
    private void AcceptButtonClicked(ActionEvent actionEvent) {
        try {
            int number = 0;
            if (!numGenerate.getText().trim().isEmpty()) {
                number = Integer.parseInt(numGenerate.getText().trim());
            }
            for (int i = 0; i < number; i++) {
                productDB.add(new Product(i, UUID.randomUUID().toString(), "товар" + i, i * 10));
            }
            gui.showOK("Успешно сгенерировано");
            gui.showMainWindow();
        } catch (NumberFormatException e) {
            gui.showError("Неверный формат данных. Введите число");
        }
    }

    public void setGUI(GUIMain gui) {
        this.gui = gui;
    }

    public void setProductDB(ProductDB productDB) {
        this.productDB = productDB;
    }
}
