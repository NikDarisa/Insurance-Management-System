package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Dao {

    // Customer-related CRUD operations
    public Customer getUserByUsernameAndPassword(String username, String password) {
        Customer customer = null;
        String query = "SELECT * FROM myinventory_system WHERE username = ? AND password = ?";

        try (Connection connection = DBConnect.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String role = resultSet.getString("role");
                customer = new Customer(username, password, role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customer;
    }

    public List<Customer> getUsers() {
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM myinventory_system";

        try (Connection connection = DBConnect.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");

                customers.add(new Customer(username, password, role));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customers;
    }

    public boolean updateUser(String oldUsername, Customer customer) {
        String query = "UPDATE myinventory_system SET username = ?, password = ?, role = ? WHERE username = ?";
        try (Connection connection = DBConnect.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, customer.getUsername());
            preparedStatement.setString(2, customer.getPassword());
            preparedStatement.setString(3, customer.getRole());
            preparedStatement.setString(4, oldUsername);
            int result = preparedStatement.executeUpdate();

            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteUser(String username) {
        String query = "DELETE FROM myinventory_system WHERE username = ?";
        try (Connection connection = DBConnect.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, username);
            int result = preparedStatement.executeUpdate();

            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Customer> getAllUsers() {
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM myinventory_system";

        try (Connection connection = DBConnect.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");

                customers.add(new Customer(username, password, role));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customers;
    }

    public boolean addUser(Customer customer) {
        String query = "INSERT INTO myinventory_system (username, password, role) VALUES (?, ?, ?)";
        try (Connection connection = DBConnect.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, customer.getUsername());
            preparedStatement.setString(2, customer.getPassword());
            preparedStatement.setString(3, customer.getRole());
            int result = preparedStatement.executeUpdate();

            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Customer getUserByUsername(String username) {
        Customer customer = null;
        String query = "SELECT * FROM myinventory_system WHERE username = ?";

        try (Connection connection = DBConnect.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");
                customer = new Customer(username, password, role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customer;
    }

    // Policy-related CRUD operations
    public List<Policy> getAllPolicies() {
        List<Policy> policies = new ArrayList<>();
        String query = "SELECT * FROM myinventory_policy";

        try (Connection connection = DBConnect.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double premium = resultSet.getDouble("premium");
                int payout = resultSet.getInt("payout");

                policies.add(new Policy(id, name, premium, payout));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return policies;
    }

    public boolean addPolicy(Policy policy) {
        String query = "INSERT INTO myinventory_policy (name, premium, payout) VALUES (?, ?, ?)";
        try (Connection connection = DBConnect.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, policy.getName());
            preparedStatement.setDouble(2, policy.getPremium());
            preparedStatement.setInt(3, policy.getPayout());
            int result = preparedStatement.executeUpdate();

            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updatePolicy(Policy policy) {
        String query = "UPDATE myinventory_policy SET name = ?, premium = ?, payout = ? WHERE id = ?";
        try (Connection connection = DBConnect.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, policy.getName());
            preparedStatement.setDouble(2, policy.getPremium());
            preparedStatement.setInt(3, policy.getPayout());
            preparedStatement.setInt(4, policy.getId());
            int result = preparedStatement.executeUpdate();

            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deletePolicy(int productId) {
        String query = "DELETE FROM myinventory_policy WHERE id = ?";
        try (Connection connection = DBConnect.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, productId);
            int result = preparedStatement.executeUpdate();

            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void decrementPolicyQuantity(int productId) {
        String query = "UPDATE myinventory_policy SET payout = payout - 1 WHERE id = ? AND payout > 0";

        try (Connection connection = DBConnect.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, productId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
