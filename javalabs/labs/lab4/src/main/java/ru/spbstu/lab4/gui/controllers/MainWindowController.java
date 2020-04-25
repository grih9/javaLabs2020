package ru.spbstu.lab4.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ru.spbstu.lab4.gui.GUIMain;
import ru.spbstu.lab4.gui.Operations;
import ru.spbstu.lab4.model.Product;
import ru.spbstu.lab4.repository.ProductDB;

import javax.crypto.spec.OAEPParameterSpec;
import java.sql.Connection;
import java.util.ArrayList;

public class MainWindowController {
    private GUIMain gui;
    private ProductDB productDB;

    @FXML
    private TableView<Product> productTable;
    @FXML
    private TableColumn<?, ?> id;
    @FXML
    private TableColumn<?, ?> prodid;
    @FXML
    private TableColumn<?, ?> title;
    @FXML
    private TableColumn<?, ?> price;

    private Connection connection;

    @FXML
    private void add() {
        openProductWindow(Operations.ADD);
    }

    @FXML
    private void edit() {
        openProductWindow(Operations.EDIT);
    }

    @FXML
    private void delete() {
        openProductWindow(Operations.DELETE);
    }

    @FXML
    private void showAll() {
        updateTable();
        gui.showOK("Все записи на экране.");
    }


    @FXML
    private void find() {
        //Product newProduct = productDB.printCost(product.getTitle());

    }

    @FXML
    private void filter() {

    }

    public void openProductWindow(Operations operation) {

    }

    public void executeOperation(Product product, Operations operation) {

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

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public TableView<Product> getProductTable() {
        return productTable;
    }

    public void updateTable() {
        productTable.getItems().clear();
        boolean f = productTable.getItems().addAll(productDB.getList());
    }
}
