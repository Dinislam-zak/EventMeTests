package ru.kpfu.itis.zakirov.eventme.dao.impl;

import ru.kpfu.itis.zakirov.eventme.dao.EventDao;
import ru.kpfu.itis.zakirov.eventme.entity.Event;
import ru.kpfu.itis.zakirov.eventme.util.DatabaseConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventDaoImpl implements EventDao {
    private final Connection connection = DatabaseConnectionUtil.getConnection();


    @Override
    public Event getById(Integer id) {
        Event event = null;
        String query = "SELECT * FROM events WHERE id = ?";
        try (connection;
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                event = new Event();
                event.setId(resultSet.getInt("id"));
                event.setTitle(resultSet.getString("title"));
                event.setDescription(resultSet.getString("description"));
                event.setDate(Timestamp.valueOf(resultSet.getTimestamp("date").toLocalDateTime()));
                event.setOrganizerId(resultSet.getInt("organizer_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return event;
    }

    @Override
    public List<Event> getAll() {
        List<Event> events = new ArrayList<>();
        String query = "SELECT * FROM events";
        try (connection;
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Event event = new Event();
                event.setId(resultSet.getInt("id"));
                event.setTitle(resultSet.getString("title"));
                event.setDescription(resultSet.getString("description"));
                event.setDate(Timestamp.valueOf(resultSet.getTimestamp("date").toLocalDateTime()));
                event.setOrganizerId(resultSet.getInt("organizer_id"));
                events.add(event);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }

    @Override
    public void save(Event event) {
        String query = "INSERT INTO events (title, description, date, organizer_id) VALUES (?, ?, ?, ?)";
        try (connection;
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, event.getTitle());
            preparedStatement.setString(2, event.getDescription());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(event.getDate().toLocalDateTime()));
            preparedStatement.setInt(4, event.getOrganizerId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Event event) {
        String query = "UPDATE events SET title = ?, description = ?, date = ?, organizer_id = ? WHERE id = ?";
        try (connection;
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, event.getTitle());
            preparedStatement.setString(2, event.getDescription());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(event.getDate().toLocalDateTime()));
            preparedStatement.setInt(4, event.getOrganizerId());
            preparedStatement.setInt(5, event.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        String query = "DELETE FROM events WHERE id = ?";
    }

    @Override
    public List<Event> getByOrganizerId(Integer organizerId) {
        return List.of();
    }
}