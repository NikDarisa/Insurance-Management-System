package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.Dao;
import models.Policy;

import java.io.IOException;

import javafx.collections.FXCollections;

public class CustomerPolicyController {

    @FXML
    private TableColumn<Policy, Integer> idColumn;
    @FXML
    private TableColumn<Policy, String> nameColumn;
    @FXML
    private TableColumn<Policy, Double> premiumColumn;
    @FXML
    private TableColumn<Policy, Integer> payoutColumn;

    @FXML
    private TableView<Policy> policyTableView;
    @FXML
    private Button createPolicyButton;
    
    @FXML
    private Button logoutButton;

    private Dao dao;

    public CustomerPolicyController() {
        dao = new Dao();
    }

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        premiumColumn.setCellValueFactory(new PropertyValueFactory<>("premium"));
        payoutColumn.setCellValueFactory(new PropertyValueFactory<>("payout"));

        loadPolicy();
        createPolicyButton.setOnAction(event -> requestPolicy());
    }

    private void loadPolicy() {
        policyTableView.setItems(FXCollections.observableArrayList(dao.getAllPolicies()));
    }

    @FXML
    private void requestPolicy() {
        Policy selectedPolicy = policyTableView.getSelectionModel().getSelectedItem();
        if (selectedPolicy != null) {
            dao.decrementPolicyQuantity(selectedPolicy.getId());
            loadPolicy();
        }
    }



    public void logout() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/LoginView.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) logoutButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
