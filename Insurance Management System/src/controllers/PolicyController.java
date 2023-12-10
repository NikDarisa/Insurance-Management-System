package controllers;

import models.Policy;
import models.Dao;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


public class PolicyController {
	
    @FXML
    private TextField policyNameTextField;

    @FXML
    private TextField policyPremiumTextField;
    
    @FXML
    private TableColumn<Policy, Integer> idColumn;
    
    @FXML
    private TableColumn<Policy, String> nameColumn;
    
    @FXML
    private TableColumn<Policy, Float> premiumColumn;
    
    @FXML
    private TableColumn<Policy, Integer> payoutColumn;

    @FXML
    private TextField policyPayoutTextField;

    @FXML
    private Button addButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TableView<Policy> policyTableView;

    private Dao dao;

    public PolicyController() {
        dao = new Dao();
    }

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        premiumColumn.setCellValueFactory(new PropertyValueFactory<>("premium"));
        payoutColumn.setCellValueFactory(new PropertyValueFactory<>("payout"));

        loadPolicies();
        addButton.setOnAction(event -> addPolicy());
        updateButton.setOnAction(event -> updatePolicy());
        deleteButton.setOnAction(event -> deletePolicy());
    }



    private void loadPolicies() {
        policyTableView.setItems(FXCollections.observableArrayList(dao.getAllPolicies()));
    }
    
    @FXML
    private void addPolicy() {
        String name = policyNameTextField.getText();
        double premium = Double.parseDouble(policyPremiumTextField.getText());
        int payout = Integer.parseInt(policyPayoutTextField.getText());

        Policy policy = new Policy(0, name, premium, payout);
        dao.addPolicy(policy);
        loadPolicies();
    }
    
    @FXML
    private void updatePolicy() {
        Policy selectedPolicy = policyTableView.getSelectionModel().getSelectedItem();
        if (selectedPolicy != null) {
            String name = policyNameTextField.getText();
            double premium = Double.parseDouble(policyPremiumTextField.getText());
            int payout = Integer.parseInt(policyPayoutTextField.getText());

            Policy updatedPolicy = new Policy(selectedPolicy.getId(), name, premium, payout);
            dao.updatePolicy(updatedPolicy);
            loadPolicies();
        }
    }
    
    @FXML
    private void deletePolicy() {
        Policy selectedpolicy = policyTableView.getSelectionModel().getSelectedItem();
        if (selectedpolicy != null) {
            dao.deletePolicy(selectedpolicy.getId());
            loadPolicies();
        }
    }
    
    @FXML
    private void onTableClicked() {
        Policy selectedItem = policyTableView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            policyNameTextField.setText(selectedItem.getName());
            policyPremiumTextField.setText(String.valueOf(selectedItem.getPremium()));
            policyPayoutTextField.setText(String.valueOf(selectedItem.getPayout()));
        }
    }

}
