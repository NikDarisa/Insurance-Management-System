package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Dao;
import models.Customer;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label statusLabel;

    private Dao dao = new Dao();

    public void login(ActionEvent event) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        Customer customer = dao.getUserByUsername(username);

        if (customer != null && customer.getPassword().equals(password)) {
            statusLabel.setText("Login successful.");


            Parent nextViewParent;
            if ("agent".equalsIgnoreCase(customer.getRole())) {
                nextViewParent = FXMLLoader.load(getClass().getResource("/views/AgentView.fxml"));
            } else {
                nextViewParent = FXMLLoader.load(getClass().getResource("/views/CustomerPolicyView.fxml"));
            }

            Scene nextViewScene = new Scene(nextViewParent);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(nextViewScene);
            window.show();
        } else {
            statusLabel.setText("Invalid username or password.");
        }
    }

    public void openSignup(ActionEvent event) throws IOException {
        Parent signupViewParent = FXMLLoader.load(getClass().getResource("/views/SignupView.fxml"));
        Scene signupViewScene = new Scene(signupViewParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(signupViewScene);
        window.show();
    }

}
