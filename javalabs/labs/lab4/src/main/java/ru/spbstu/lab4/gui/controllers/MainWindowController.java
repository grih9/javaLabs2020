package ru.spbstu.lab4.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.spbstu.lab4.gui.GUIMain;
import ru.spbstu.lab4.gui.Operations;
import ru.spbstu.lab4.model.Product;
import ru.spbstu.lab4.repository.ProductDB;

import javax.crypto.spec.OAEPParameterSpec;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

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

    private Connection connection;

    @FXML
    private void add() {
        showProductWindow(Operations.ADD);
    }

    @FXML
    private void edit() {
        showProductWindow(Operations.EDIT);
    }

    @FXML
    private void delete() {
        showProductWindow(Operations.DELETE);
    }

    @FXML
    private void showAll() {
        updateTable();
        gui.showOK("Все записи на экране.");
    }


    @FXML
    private void find() {
        showProductWindow(Operations.FIND);
        //Product newProduct = productDB.printCost(product.getTitle());

    }

    @FXML
    private void filter() {
        showProductWindow(Operations.FILTER);
    }

    private void showProductWindow(Operations operation) {
        try {
            final FXMLLoader loader = new FXMLLoader(getClass().getResource("/ProductWindow.fxml"));
            Stage productWindow = new Stage();
            productWindow.setScene(new Scene(loader.load()));
            productWindow.setTitle("Продукт");
            productWindow.setResizable(false);
            productWindow.initModality(Modality.APPLICATION_MODAL);

            productWindow.show();

            final ProductWindowController controller = loader.getController();
            controller.setParentDetails(this, operation);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        boolean f = productTable.getItems().addAll(productDB.getTableList());
    }
}
