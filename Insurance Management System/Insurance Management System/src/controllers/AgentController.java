package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import models.Dao;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class AgentController {
    @FXML
    private TextField policyIdField, stockField;
    @FXML
    private Label statusLabel;
    
    @FXML
    private Button logoutButton;
    
    private Dao dao = new Dao();

//    public void addInventory() {
//        int productId = Integer.parseInt(policyIdField.getText());
//        int stock = Integer.parseInt(stockField.getText());
//
//        Inventory inventory = new Inventory(0, productId, stock);
//
//        Inventory addedInventory = dao.addInventory(inventory);
//        if (addedInventory != null) {
//            statusLabel.setText("Policy added successfully.");
//        } else {
//            statusLabel.setText("Error adding policy.");
//        }
//    }
    

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
