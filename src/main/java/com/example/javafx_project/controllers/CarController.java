package com.example.javafx_project.controllers;

import com.example.javafx_project.entities.Car;
import com.example.javafx_project.services.CarService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class CarController {
    @FXML
    private TableView<Car> carTableView;

    private CarService carService;

    private ObservableList<Car> carsList;

    public CarController() {
        carService = new CarService();
    }

    @FXML
    private void initialize() {
        // Initialize the cars list
        carsList = FXCollections.observableArrayList(carService.findAll());

        // Set the items of the table view to the cars list
        carTableView.setItems(carsList);

        // Configure table columns
        TableColumn<Car, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Car, String> brandColumn = new TableColumn<>("Brand");
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));

        TableColumn<Car, String> modelColumn = new TableColumn<>("Model");
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));

        TableColumn<Car, String> registrationNumberColumn = new TableColumn<>("Registration Number");
        registrationNumberColumn.setCellValueFactory(new PropertyValueFactory<>("registrationNumber"));

        TableColumn<Car, String> registrationDateColumn = new TableColumn<>("Registration Date");
        registrationDateColumn.setCellValueFactory(new PropertyValueFactory<>("registrationDate"));

        TableColumn<Car, String> colorColumn = new TableColumn<>("Color");
        colorColumn.setCellValueFactory(new PropertyValueFactory<>("color"));

        TableColumn<Car, Car.FuelType> fuelTypeColumn = new TableColumn<>("Fuel Type");
        fuelTypeColumn.setCellValueFactory(new PropertyValueFactory<>("fuelType"));

        TableColumn<Car, String> ownerColumn = new TableColumn<>("Owner");
        ownerColumn.setCellValueFactory(new PropertyValueFactory<>("owner"));

        // Add the columns to the table view
        carTableView.getColumns().addAll(idColumn, brandColumn, modelColumn, registrationNumberColumn,
                registrationDateColumn, colorColumn, fuelTypeColumn, ownerColumn);
    }
}
