package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.BaseEntity;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;

import java.time.LocalDateTime;

/**
 * GKislin
 * 13.03.2015.
 */
public class MealTestData {

    public static final int FIRST_MEAL = BaseEntity.START_SEQ + 2;
    public static final int SECOND_MEAL = BaseEntity.START_SEQ + 3;
    public static final int THIRD_MEAL = BaseEntity.START_SEQ + 4;
    public static final int FOURTH_MEAL = BaseEntity.START_SEQ + 5;

    public static final Meal FIRST_ADMIN_MEAL = new Meal(FIRST_MEAL, LocalDateTime.of(2016, 10, 5, 10, 0, 0), "админ завтрак", 10);
    public static final Meal SECOND_ADMIN_MEAL = new Meal(SECOND_MEAL, LocalDateTime.of(2016, 10, 5, 19, 0, 0), "админ ужин", 3000);
    public static final Meal FIRST_USER_MEAL = new Meal(THIRD_MEAL, LocalDateTime.of(2016, 10, 4, 13, 0, 0), "user break", 500);
    public static final Meal SECOND_USER_MEAL = new Meal(FOURTH_MEAL, LocalDateTime.of(2016, 10, 5, 18, 0, 0), "user dinner", 1500);

    public static final ModelMatcher<Meal> MATCHER = new ModelMatcher<>();

}
