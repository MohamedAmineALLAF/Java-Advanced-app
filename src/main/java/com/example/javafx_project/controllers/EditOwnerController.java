package com.example.javafx_project.controllers;

import com.example.javafx_project.entities.Owner;
import com.example.javafx_project.services.OwnerService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class EditOwnerController  {
    private Owner owner;
    private boolean updateRequired = false;

    @FXML
    private TextField nameField;
    @FXML
    private TextField cinField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField phoneNumberField;

    private OwnerService ownerService; // Assuming you have the OwnerService injected or instantiated properly

    public void setOwnerService(OwnerService ownerService) {
        this.ownerService = ownerService;
    }


    public void setOwner(Owner owner) {
        this.owner = owner;
        populateFields();
    }

    public boolean isUpdateRequired() {
        return updateRequired;
    }

    public Owner getUpdatedOwner() {
        updateOwner();
        return owner;
    }

    private void populateFields() {
        if (owner != null) {
            nameField.setText(owner.getName());
            cinField.setText(owner.getCIN());
            addressField.setText(owner.getAddress());
            phoneNumberField.setText(owner.getPhoneNumber().toString());
        }
    }

    private void updateOwner() {
        if (owner != null) {
            owner.setName(nameField.getText());
            owner.setCIN(cinField.getText());
            owner.setAddress(addressField.getText());
            owner.setPhoneNumber(Integer.parseInt(phoneNumberField.getText()));
            updateRequired = true;
        }
    }

    @FXML
    private void saveChanges() {
        updateOwner();
        ownerService.update(owner); // Assuming you have the update method in your OwnerService implementation
        closeForm();
    }

    private void closeForm() {
        // Close the form or perform any necessary cleanup
        nameField.getScene().getWindow().hide();
    }
}
