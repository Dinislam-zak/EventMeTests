package ru.kpfu.itis.zakirov.eventme.dao.impl;

import ru.kpfu.itis.zakirov.eventme.dao.UserDao;
import ru.kpfu.itis.zakirov.eventme.entity.User;
import ru.kpfu.itis.zakirov.eventme.entity.Role;
import ru.kpfu.itis.zakirov.eventme.util.DatabaseConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {


    @Override
    public User getById(Integer id) {
        try (Connection connection = DatabaseConnectionUtil.getConnection()){
            String sql = "SELECT * FROM users WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet != null && resultSet.next()) {
                return new User(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        new Role(resultSet.getInt("role_id"), null),
                        new ArrayList<>(),
                        new ArrayList<>(),
                        resultSet.getString("avatar_url")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while fetching user by ID", e);
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        try (Connection connection = DatabaseConnectionUtil.getConnection()){
            String sql = "SELECT * FROM users";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            List<User> users = new ArrayList<>();
            while (resultSet != null && resultSet.next()) {
                users.add(new User(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        new Role(resultSet.getInt("role_id"), null),
                        new ArrayList<>(),
                        new ArrayList<>(),
                        resultSet.getString("avatar_url")
                ));
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException("Error while fetching all users", e);
        }
    }

    @Override
    public void save(User user) {
        try (Connection connection = DatabaseConnectionUtil.getConnection();) {
            String sql = "INSERT INTO users (username, email, password, role_id) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setInt(4, 1);
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet != null && resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while saving user", e);
        }
    }

    @Override
    public void update(User user) {
        try (Connection connection = DatabaseConnectionUtil.getConnection();){
            String sql = "UPDATE users SET username = ?, email = ?, password = ?, role_id = ?, avatar_url = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setInt(4, user.getRole().getId());
            statement.setInt(5, user.getId());
            statement.setString(6, user.getAvatarUrl());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error while updating user", e);
        }
    }

    @Override
    public void delete(Integer id) {
        try (Connection connection = DatabaseConnectionUtil.getConnection();){
            String sql = "DELETE FROM users WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error while deleting user", e);
        }
    }

    @Override
    public User getByUsername(String username) {
        try (Connection connection = DatabaseConnectionUtil.getConnection();){
            String sql = "SELECT * FROM users WHERE username = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet != null && resultSet.next()) {
                return new User(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        new Role(resultSet.getInt("role_id"), null),
                        new ArrayList<>(),
                        new ArrayList<>(),
                        resultSet.getString("avatar_url")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while fetching user by email", e);
        }
        return null;
    }

    @Override
    public void updateAvatar(String username, String avatarUrl) throws SQLException {
        try (Connection connection = DatabaseConnectionUtil.getConnection();){
            String sql = "UPDATE users SET avatar_url = ? WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, avatarUrl);
            preparedStatement.setString(2, username);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating avatar URL", e);
        }
    }
}