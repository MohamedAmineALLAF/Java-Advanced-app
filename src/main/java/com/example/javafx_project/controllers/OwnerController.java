package com.example.javafx_project.controllers;

import com.example.javafx_project.entities.Owner;
import com.example.javafx_project.services.OwnerService;
import com.example.javafx_project.services.UserService;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class OwnerController implements Initializable {
    @FXML
    private TableView<Owner> ownerTableView;

    @FXML
    private TableColumn<Owner, Integer> idColumn;

    @FXML
    private TableColumn<Owner, String> nameColumn;

    @FXML
    private TableColumn<Owner, String> cinColumn;

    @FXML
    private TableColumn<Owner, String> addressColumn;

    @FXML
    private TableColumn<Owner, Integer> phoneNumberColumn;
    @FXML
    private TableColumn<Owner, Button> editColumn;
    @FXML
    private TableColumn<Owner, Button> deleteColumn;

    private OwnerService ownerService;
    private ObservableList<Owner> ownerList;

    public void setOwnerService(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @FXML
    private void openOwnerForm() {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/views/OwnerForm.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ownerService = new OwnerService();
        startAutoRefresh();
        loadOwners();
        setupTableView();

    }

    private void loadOwners() {
        List<Owner> owners = ownerService.findAll();
        ownerList = FXCollections.observableArrayList(owners);
    }

    private void setupTableView() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        cinColumn.setCellValueFactory(new PropertyValueFactory<>("CIN"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        editColumn.setCellFactory(createEditButtonCellFactory());

        deleteColumn.setCellFactory(createDeleteButtonCellFactory());

        ownerTableView.setItems(ownerList);
    }

    @FXML
    private void openEditOwnerForm() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/EditOwner.fxml"));
            Parent parent = loader.load();
            EditOwnerController editOwnerController = loader.getController();
            Owner selectedOwner = ownerTableView.getSelectionModel().getSelectedItem();
            editOwnerController.setOwner(selectedOwner);
            editOwnerController.setOwnerService(ownerService); // Pass the OwnerService instance to the controller

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(parent));
            stage.showAndWait();

            // After the EditOwnerForm is closed, you can handle the update if needed
            if (editOwnerController.isUpdateRequired()) {
                Owner updatedOwner = editOwnerController.getUpdatedOwner();
                ownerService.update(updatedOwner); // Perform the update operation using the updatedOwner object
                // Refresh the table view or any other necessary actions
                loadOwners();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    private Callback<TableColumn<Owner, Button>, TableCell<Owner, Button>> createEditButtonCellFactory() {
        return new Callback<>() {
            @Override
            public TableCell<Owner, Button> call(TableColumn<Owner, Button> column) {
                return new TableCell<>() {
                    private final Button editButton = new Button("Edit");

                    {
                        editButton.setOnAction(event -> {
                            Owner owner = getTableView().getItems().get(getIndex());
                            // Handle edit button action
                            // You can open a dialog or switch to an edit view
                        });
                    }

                    @Override
                    protected void updateItem(Button item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!empty) {
                            setGraphic(editButton);
                        } else {
                            setGraphic(null);
                        }
                    }
                };
            }
        };
    }

    private Callback<TableColumn<Owner, Button>, TableCell<Owner, Button>> createDeleteButtonCellFactory() {
        return new Callback<>() {
            @Override
            public TableCell<Owner, Button> call(TableColumn<Owner, Button> column) {
                return new TableCell<>() {
                    private final Button deleteButton = new Button("Delete");

                    {
                        deleteButton.setOnAction(event -> {
                            Owner owner = getTableView().getItems().get(getIndex());
                            ownerService.remove(owner);
                            ownerTableView.getItems().remove(owner);
                        });
                    }

                    @Override
                    protected void updateItem(Button item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!empty) {
                            setGraphic(deleteButton);
                        } else {
                            setGraphic(null);
                        }
                    }
                };
            }
        };
    }

    private void startAutoRefresh() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
            refreshTableView();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void refreshTableView() {
        List<Owner> owners = ownerService.findAll();
        ownerTableView.getItems().clear();
        ownerTableView.getItems().addAll(owners);
    }


}
