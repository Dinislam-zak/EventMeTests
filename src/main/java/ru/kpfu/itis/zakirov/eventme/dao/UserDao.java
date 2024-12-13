package ru.kpfu.itis.zakirov.eventme.dao;

import ru.kpfu.itis.zakirov.eventme.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    User getById(Integer id);
    List<User> getAll();
    void save(User user);
    void update(User user);
    void delete(Integer id);
    User getByUsername(String username);
    void updateAvatar(String username, String avatarUrl) throws SQLException;
}
