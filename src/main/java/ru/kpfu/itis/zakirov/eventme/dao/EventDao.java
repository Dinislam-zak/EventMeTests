package ru.kpfu.itis.zakirov.eventme.dao;

import ru.kpfu.itis.zakirov.eventme.entity.Event;

import java.util.List;

public interface EventDao {
    Event getById(Integer id);
    List<Event> getAll();
    void save(Event event);
    void update(Event event);
    void delete(Integer id);
    List<Event> getByOrganizerId(Integer organizerId);
}
