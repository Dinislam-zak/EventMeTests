package ru.kpfu.itis.zakirov.eventme.dao.impl;


import ru.kpfu.itis.zakirov.eventme.dao.RoleDao;
import ru.kpfu.itis.zakirov.eventme.entity.Role;
import ru.kpfu.itis.zakirov.eventme.util.DatabaseConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoleDaoImpl implements RoleDao {
    private final Connection connection = DatabaseConnectionUtil.getConnection();

    @Override
    public Role getById(Integer id) {
        String sql = "SELECT * FROM roles WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Role(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while fetching role by ID", e);
        }
        return null;
    }

    @Override
    public List<Role> getAll() {
        String sql = "SELECT * FROM roles";
        List<Role> roles = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                roles.add(new Role(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while fetching all roles", e);
        }
        return roles;
    }

    @Override
    public void save(Role role) {
        String sql = "INSERT INTO roles (name) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, role.getName());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Failed to save role, no rows affected.");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    role.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Failed to save role, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while saving role", e);
        }
    }

    @Override
    public void update(Role role) {
        String sql = "UPDATE roles SET name = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, role.getName());
            statement.setInt(2, role.getId());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Failed to update role, no rows affected.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while updating role", e);
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM roles WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Failed to delete role, no rows affected.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while deleting role", e);
        }
    }
}
