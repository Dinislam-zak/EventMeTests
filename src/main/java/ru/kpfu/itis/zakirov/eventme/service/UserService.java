package ru.kpfu.itis.zakirov.eventme.service;

import java.util.List;
import ru.kpfu.itis.zakirov.eventme.dto.UserLoginDto;
import ru.kpfu.itis.zakirov.eventme.dto.UserDto;
import ru.kpfu.itis.zakirov.eventme.entity.Role;

public interface UserService {

    UserLoginDto getUserLoginDto(String email);

    List<UserDto> getAll();

    UserDto get(Integer id);

    UserDto getByLogin(String username);

    void register(String name, String email, String password, Role role);
}
