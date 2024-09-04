package com.cts.dao.impl;

import com.cts.dao.SupplierDAO;
import com.cts.model.Supplier;
import com.cts.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAOImpl implements SupplierDAO {

    @Override
    public void addSupplier(Supplier supplier) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO Supplier (name, email, phone_number, address) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, supplier.getName());
            preparedStatement.setString(2, supplier.getEmail());
            preparedStatement.setString(3, supplier.getPhoneNumber());
            preparedStatement.setString(4, supplier.getAddress());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Supplier getSupplierById(int supplierId) {
        Supplier supplier = null;
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM Supplier WHERE supplier_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, supplierId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                supplier = new Supplier(
                        resultSet.getInt("supplier_id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("phone_number"),
                        resultSet.getString("address")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return supplier;
    }

    @Override
    public List<Supplier> getAllSuppliers() {
        List<Supplier> suppliers = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM Supplier";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                suppliers.add(new Supplier(
                        resultSet.getInt("supplier_id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("phone_number"),
                        resultSet.getString("address")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return suppliers;
    }

    @Override
    public void updateSupplier(Supplier supplier) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "UPDATE Supplier SET name = ?, email = ?, phone_number = ?, address = ? WHERE supplier_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, supplier.getName());
            preparedStatement.setString(2, supplier.getEmail());
            preparedStatement.setString(3, supplier.getPhoneNumber());
            preparedStatement.setString(4, supplier.getAddress());
            preparedStatement.setInt(5, supplier.getSupplierId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteSupplier(int supplierId) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "DELETE FROM Supplier WHERE supplier_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, supplierId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
