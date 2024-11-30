package ru.kpfu.itis.zakirov.eventme.dao;

import ru.kpfu.itis.zakirov.eventme.entity.Review;
import java.util.List;

public interface ReviewDao {
    Review getById(Integer id);
    List<Review> getByEventId(Integer eventId);
    void save(Review review);
    void update(Review review);
    void delete(Integer id);
}
