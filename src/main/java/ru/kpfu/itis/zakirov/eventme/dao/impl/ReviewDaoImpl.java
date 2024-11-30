package ru.kpfu.itis.zakirov.eventme.dao.impl;

import ru.kpfu.itis.zakirov.eventme.dao.ReviewDao;
import ru.kpfu.itis.zakirov.eventme.entity.Review;
import ru.kpfu.itis.zakirov.eventme.entity.Event;
import ru.kpfu.itis.zakirov.eventme.entity.User;
import ru.kpfu.itis.zakirov.eventme.util.DatabaseConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewDaoImpl implements ReviewDao {
    private final Connection connection = DatabaseConnectionUtil.getConnection();

    @Override
    public Review getById(Integer id) {
        String sql = "SELECT * FROM reviews WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToReview(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while fetching review by ID", e);
        }
        return null;
    }

    @Override
    public List<Review> getByEventId(Integer eventId) {
        String sql = "SELECT * FROM reviews WHERE event_id = ?";
        List<Review> reviews = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, eventId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                reviews.add(mapResultSetToReview(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while fetching reviews by event ID", e);
        }
        return reviews;
    }

    @Override
    public void save(Review review) {
        String sql = "INSERT INTO reviews (comment, rating, event_id, user_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, review.getComment());
            statement.setInt(2, review.getRating());
            statement.setInt(3, review.getEvent().getId());
            statement.setInt(4, review.getUser().getId());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Failed to save review, no rows affected.");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    review.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Failed to save review, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while saving review", e);
        }
    }

    @Override
    public void update(Review review) {
        String sql = "UPDATE reviews SET comment = ?, rating = ?, event_id = ?, user_id = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, review.getComment());
            statement.setInt(2, review.getRating());
            statement.setInt(3, review.getEvent().getId());
            statement.setInt(4, review.getUser().getId());
            statement.setInt(5, review.getId());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Failed to update review, no rows affected.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while updating review", e);
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM reviews WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Failed to delete review, no rows affected.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while deleting review", e);
        }
    }

    private Review mapResultSetToReview(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String comment = resultSet.getString("comment");
        int rating = resultSet.getInt("rating");

        EventDaoImpl eventDao = new EventDaoImpl();
        Event event = eventDao.getById(resultSet.getInt("event_id"));
        UserDaoImpl userDao = new UserDaoImpl();
        User user = userDao.getById(resultSet.getInt("user_id"));

        return new Review(id, user, event, rating, comment);
    }
}
