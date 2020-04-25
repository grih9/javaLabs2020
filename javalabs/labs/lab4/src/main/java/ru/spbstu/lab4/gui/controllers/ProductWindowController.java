package ru.spbstu.lab4.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import ru.spbstu.lab4.gui.GUIMain;
import ru.spbstu.lab4.gui.Operations;
import ru.spbstu.lab4.model.Product;

import java.util.UUID;

public class ProductWindowController {
    @FXML
    public Label fromLabel;
    @FXML
    public Label toLabel;
    @FXML
    public Label titleLabel;
    @FXML
    public Label priceLabel;
    @FXML
    public TextField titleText;
    @FXML
    private TextField toText;
    @FXML
    private TextField priceText;
    @FXML
    private TextField fromText;
    @FXML
    private Label typeOperLabel;

    private GUIMain gui;
    private MainWindowController parent;
    private Operations operation;

    public void setGUI(GUIMain gui) {
        this.gui = gui;
    }

    public void setParentDetails(MainWindowController parent, Operations operation) {
        this.parent = parent;
        this.operation = operation;

        switch (operation) {
            case ADD:
                typeOperLabel.setText("ДОБАВЛЕНИЕ ПРОДУКТА");
                typeOperLabel.setTextAlignment(TextAlignment.CENTER);
                titleText.setEditable(true);
                priceText.setEditable(true);
                break;
            case EDIT:
                typeOperLabel.setText("ИЗМЕНЕНИЕ ПРОДУКТА");
                typeOperLabel.setTextAlignment(TextAlignment.CENTER);
                priceLabel.setText("НОВАЯ ЦЕНА");
                priceLabel.setTextAlignment(TextAlignment.CENTER);
                titleText.setEditable(true);
                priceText.setEditable(true);
                break;
            case DELETE:
                typeOperLabel.setText("УДАЛЕНИЕ ПРОДУКТА");
                typeOperLabel.setTextAlignment(TextAlignment.CENTER);
                titleText.setEditable(true);
                priceText.setVisible(false);
                priceLabel.setVisible(false);
                break;
            case FIND:
                typeOperLabel.setText("ПОИСК ПРОДУКТА");
                typeOperLabel.setTextAlignment(TextAlignment.CENTER);
                titleText.setEditable(true);
                priceText.setVisible(false);
                priceLabel.setVisible(false);
                break;
            case FILTER:
                typeOperLabel.setText("ФИЛЬТР ПО ЦЕНЕ");
                typeOperLabel.setTextAlignment(TextAlignment.CENTER);
                titleLabel.setVisible(false);
                titleText.setVisible(false);
                priceText.setVisible(false);
                priceLabel.setVisible(false);
                toLabel.setVisible(true);
                toText.setVisible(true);
                fromLabel.setVisible(true);
                fromText.setVisible(true);
                break;
        }
    }

    @FXML
    private void clickOnButtonEvent(ActionEvent actionEvent) {
        try {
            String title = "товар";
            if (titleText.isVisible()) {
                title = titleText.getText().trim();

                if (title.contains(" ")) {
                    throw new IllegalArgumentException();
                }
            }

            double price = 0;
            if (priceText.isVisible()) {
                if (!priceText.getText().trim().isEmpty()) {
                    price = Double.parseDouble(priceText.getText().trim());
                }
            }

            double priceFrom = 0;
            double priceTo = 0;
            if (fromText.isVisible()) {
                if (!fromText.getText().trim().isEmpty()) {
                    priceFrom = Double.parseDouble(fromText.getText().trim());
                }

                if (!toText.getText().trim().isEmpty()) {
                    priceTo = Double.parseDouble(toText.getText().trim());
                }
            }

            parent.executeOperation(new Product(0, UUID.randomUUID().toString(), title, price), operation, priceFrom, priceTo);

            Stage window = (Stage) fromLabel.getScene().getWindow();
            window.close();
        } catch (IllegalArgumentException e) {
            gui.showError("Неверный ввод данных");
        }
    }
}
