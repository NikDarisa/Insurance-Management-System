package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Dao;
import models.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;

public class CustomerController {

    @FXML
    private TextField usernameTextField;
    
    @FXML
    private TableColumn<Customer, String> usernameColumn;
    
    @FXML
    private TableColumn<Customer, String> roleColumn;
    
    @FXML
    private PasswordField passwordField;

    @FXML
    private ComboBox<String> roleComboBox;

    @FXML
    private Button addUserButton;

    @FXML
    private Button updateUserButton;

    @FXML
    private Button deleteUserButton;

    @FXML
    private TableView<Customer> usersTableView;
    
    private Dao dao;

    public CustomerController() {
        dao = new Dao();
    }

    @FXML
    public void initialize() {
    	usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
    	roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
        
        ObservableList<String> roleList = FXCollections.observableArrayList("agent", "customer");
        roleComboBox.setItems(roleList);
        loadUsers();
        addUserButton.setOnAction(event -> addUser());
        updateUserButton.setOnAction(event -> updateUser());
        deleteUserButton.setOnAction(event -> deleteUser());
    }

    private void loadUsers() {
        usersTableView.setItems(FXCollections.observableArrayList(dao.getUsers()));
    }

    @FXML
    private void addUser() {
        String username = usernameTextField.getText();
        String password = passwordField.getText();
        String role = roleComboBox.getSelectionModel().getSelectedItem();

        Customer customer = new Customer(username, password, role);
        dao.addUser(customer);
        loadUsers();
    }

    @FXML
    private void updateUser() {
        Customer selectedUser = usersTableView.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            String username = usernameTextField.getText();
            String password = passwordField.getText();
            String role = roleComboBox.getSelectionModel().getSelectedItem();

            Customer updatedUser = new Customer(username, password, role);
            dao.updateUser(selectedUser.getUsername(), updatedUser); // Pass the old username as an additional parameter
            loadUsers();
        }
    }

    @FXML
    private void deleteUser() {
        Customer selectedUser = usersTableView.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            dao.deleteUser(selectedUser.getUsername());
            loadUsers();
        }
    }
    
    @FXML
    private void onTableClicked() {
        Customer selectedItem = usersTableView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            usernameTextField.setText(selectedItem.getUsername());
            passwordField.setText(selectedItem.getPassword());
            roleComboBox.setValue(selectedItem.getRole());
        }
    }

}
