package ru.spbstu.lab4.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ru.spbstu.lab4.gui.Operations;

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

    MainWindowController parent;
    Operations operation;

    public void setParentDetails(MainWindowController parent, Operations operation) {
        this.parent = parent;
        this.operation = operation;

        switch (operation) {
            case ADD:
                typeOperLabel.setText("ДОБАВЛЕНИЕ ПРОДУКТА");
                titleText.setEditable(true);
                priceText.setEditable(true);
                break;
            case EDIT:
                typeOperLabel.setText("ИЗМЕНЕНИЕ ПРОДУКТА");
                priceLabel.setText("НОВАЯ ЦЕНА");
                titleText.setEditable(true);
                priceText.setEditable(true);
                break;
            case DELETE:
                typeOperLabel.setText("УДАЛЕНИЕ ПРОДУКТА");
                titleText.setEditable(true);
                priceText.setVisible(false);
                priceLabel.setVisible(false);
                break;
            case FIND:
                typeOperLabel.setText("ИЗМЕНЕНИЕ ПРОДУКТА");
                titleText.setEditable(true);
                priceText.setVisible(false);
                priceLabel.setVisible(false);
                break;
            case FILTER:
                typeOperLabel.setText("ФИЛЬТР ПО ЦЕНЕ");
                titleLabel.setVisible(false);
                titleText.setVisible(false);
                priceText.setVisible(false);
                priceLabel.setVisible(false);
                toLabel.setVisible(true);
                toText.setVisible(true);
                fromLabel.setVisible(true);
                fromLabel.setVisible(true);
                break;
        }
    }

    @FXML
    private void clickOnButtonEvent(ActionEvent actionEvent) {

    }
}
