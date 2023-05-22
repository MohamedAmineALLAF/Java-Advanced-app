package com.example.javafx_project.controllers;

import com.example.javafx_project.entities.Owner;
import com.example.javafx_project.services.OwnerService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AddOwnerController implements Initializable {
    @FXML
    private TextField nameField;

    @FXML
    private TextField cinField;

    @FXML
    private TextField addressField;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private Button addButton;

    private OwnerService ownerService;

    public void setOwnerService(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ownerService = new OwnerService();
    }

    @FXML
    private void handleAddButtonAction() {
        String name = nameField.getText();
        String cin = cinField.getText();
        String address = addressField.getText();
        int phoneNumber = Integer.parseInt(phoneNumberField.getText());

        Owner owner = new Owner();
        owner.setName(name);
        owner.setCIN(cin);
        owner.setAddress(address);
        owner.setPhoneNumber(phoneNumber);

        ownerService.save(owner);

        clearFields();
    }

    private void clearFields() {
        nameField.clear();
        cinField.clear();
        addressField.clear();
        phoneNumberField.clear();
    }


}
