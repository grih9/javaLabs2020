package ru.spbstu.lab4.gui.controllers;

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

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

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
            controller.setGUI(gui);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void executeOperation(Product product, Operations operation, double priceFrom, double priceTo) {
        switch (operation) {
            case ADD:
                productDB.add(product);
                updateTable();
                break;
            case EDIT:
                productDB.changeCost(product.getTitle(), product.getCost());
                updateTable();
                break;
            case DELETE:
                productDB.removeByTitle(product.getTitle());
                updateTable();
                break;
            case FIND:
                Product productToFind = productDB.findProduct(product.getTitle());
                if (productToFind == null) {
                    throw new NoSuchElementException();
                }
                productTable.getItems().clear();
                productTable.getItems().add(productToFind);
                break;
            case FILTER:
                List<Product> filteredTableList = productDB.getFilteredTableList(priceFrom, priceTo);
                productTable.getItems().clear();
                productTable.getItems().addAll(filteredTableList);
                break;
        }
        gui.showOK("Операция: " + operation.toString() + " выполнена успешно!");
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

    public void updateTable() {
        productTable.getItems().clear();
        productTable.getItems().addAll(productDB.getTableList());
    }
}
