package ru.spbstu.lab4.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ru.spbstu.lab4.gui.GUIMain;
import ru.spbstu.lab4.model.Product;
import ru.spbstu.lab4.repository.ProductDB;

public class MainWindowController {
    private GUIMain gui;
    private ProductDB productDB;

    @FXML
    private TableView<Product> productTable;
    @FXML
    private TableColumn<Product, Integer> id;
    @FXML
    private TableColumn<Product, String> prodid;
    @FXML
    private TableColumn<Product, String> title;
    @FXML
    private TableColumn<Product, Double> price;

    @FXML
    private void add() {

    }

    @FXML
    private void edit() {

    }

    @FXML
    private void delete() {

    }

    @FXML
    private void showAll() {

    }

    @FXML
    private void find() {
        Product newProduct = productDB.printCost(product.getTitle());

    }

    @FXML
    private void filter() {

    }

    public void setProductDB(ProductDB productDB) {
        this.productDB = productDB;
    }

    public void setGUI(GUIMain gui) {
        this.gui = gui;
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        prodid.setCellValueFactory(new PropertyValueFactory<>("prodid"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        price.setCellValueFactory(new PropertyValueFactory<>("cost"));

    }

    public TableView<Product> getProductTable() {
        return productTable;
    }


}
