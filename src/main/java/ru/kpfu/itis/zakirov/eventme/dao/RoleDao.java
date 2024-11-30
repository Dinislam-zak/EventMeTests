package ru.kpfu.itis.zakirov.eventme.dao;


import ru.kpfu.itis.zakirov.eventme.entity.Role;

import java.util.List;

public interface RoleDao {
    Role getById(Integer id);
    List<Role> getAll();
    void save(Role role);
    void update(Role role);
    void delete(Integer id);
}
