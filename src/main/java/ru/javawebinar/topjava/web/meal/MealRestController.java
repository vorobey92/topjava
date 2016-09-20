package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
@Controller
public class MealRestController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MealService service;

    public Meal save(Meal meal) {
        return service.save(meal);
    }

    public Meal get(int id) {
        return service.get(id, AuthorizedUser.getId());
    }

    public void delete(int id) {
        service.delete(id, AuthorizedUser.getId());
    }

    public List<MealWithExceed> getAll() {
        return service.getAll(AuthorizedUser.getId());
    }

    public List<MealWithExceed> getBetween(LocalDate dateStart, LocalDate dateEnd,
                                           LocalTime timeStart, LocalTime timeEnd) {
        if (dateEnd == null) {
            dateEnd = LocalDate.MAX;
        }
        if (dateStart == null) {
            dateStart = LocalDate.MIN;
        }
        if (timeEnd == null) {
            timeEnd = LocalTime.MAX;
        }
        if (timeStart == null) {
            timeStart = LocalTime.MIN;
        }
        return service.getBetweenDateTime(LocalDateTime.of(dateStart, timeStart), LocalDateTime.of(dateEnd, timeEnd), AuthorizedUser.getId());
    }

}
