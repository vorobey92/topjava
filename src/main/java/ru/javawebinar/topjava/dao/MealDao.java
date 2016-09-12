package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;

import java.util.List;

/**
 * Created by ia.vorobev on 10.09.2016.
 */
public interface MealDao {

    List<Meal> getMeals();

    void addMeal(Meal meal);

    void updateMeal(Meal meal);

    void removeMealById(int id);

    Meal getMealById(int mealId);
}
