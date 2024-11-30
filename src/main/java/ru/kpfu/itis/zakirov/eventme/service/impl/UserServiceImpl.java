package ru.kpfu.itis.zakirov.eventme.service.impl;

import ru.kpfu.itis.zakirov.eventme.dao.UserDao;
import ru.kpfu.itis.zakirov.eventme.dao.impl.UserDaoImpl;
import ru.kpfu.itis.zakirov.eventme.dto.UserDto;
import ru.kpfu.itis.zakirov.eventme.dto.UserLoginDto;
import ru.kpfu.itis.zakirov.eventme.entity.Role;
import ru.kpfu.itis.zakirov.eventme.entity.User;
import ru.kpfu.itis.zakirov.eventme.service.UserService;
import ru.kpfu.itis.zakirov.eventme.util.PasswordUtil;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDao userDao = new UserDaoImpl();

    @Override
    public UserLoginDto getUserLoginDto(String username) {
        User user = userDao.getByUsername(username);
        if (user == null) {
            return null;
        }
        return new UserLoginDto(
                user.getUsername(),
                user.getPassword()
        );
    }

    @Override
    public UserDto get(Integer id) {
        User user = userDao.getById(id);
        return new UserDto(
                user.getUsername(),
                user.getEmail(),
                user.getPassword()
        );
    }

    @Override
    public UserDto getByLogin(String username) {
        User user = userDao.getByUsername(username);
        return new UserDto(
                user.getUsername(),
                user.getEmail(),
                user.getPassword()
        );
    }

    @Override
    public List<UserDto> getAll() {
        List<User> users = userDao.getAll();
        return users.stream()
                .map(u -> new UserDto(u.getUsername(), u.getEmail(),u.getPassword()))
                .toList();
    }

    @Override
    public void register(String username, String email, String password, Role role) {
        userDao.save(
                new User(
                        null,
                        username,
                        email,
                        PasswordUtil.encrypt(password),
                        role,
                        null,
                        null
                )
        );
    }
}