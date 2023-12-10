package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import models.Dao;
import models.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.IOException;

public class SignupController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    
    @FXML
    private ComboBox<String> roleComboBox;

    @FXML
    private TextField roleField;
    @FXML
    private Label statusLabel;


    private Dao dao = new Dao();
    
    @FXML
    public void initialize() {
        ObservableList<String> roleOptions = FXCollections.observableArrayList("Agent", "Customer");
        roleComboBox.setItems(roleOptions);
        roleComboBox.getSelectionModel().select("Customer"); 
    }
    
    public void signup(ActionEvent event) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String role = roleComboBox.getValue();

        Customer existingUser = dao.getUserByUsername(username);
        if (existingUser != null) {
            statusLabel.setText("Username already exists.");
        } else {
            Customer newUser = new Customer(username, password, role);
            if (dao.addUser(newUser)) {
                statusLabel.setText("Customer added successfully! Redirecting to login...");

                Parent loginViewParent = FXMLLoader.load(getClass().getResource("/views/LoginView.fxml"));
                Scene loginViewScene = new Scene(loginViewParent);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(loginViewScene);
                window.show();
            } else {
                statusLabel.setText("Error: Could not add user.");
            }
        }
    }

}
