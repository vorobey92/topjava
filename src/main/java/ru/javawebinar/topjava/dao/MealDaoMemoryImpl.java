package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Created by ia.vorobev on 10.09.2016.
 */
public class MealDaoMemoryImpl implements MealDao {

    private static volatile List<Meal> meals = new ArrayList<>();;
    private static volatile AtomicInteger mealsId = new AtomicInteger(0);

    @Override
    public List<Meal> getMeals() {
        return new ArrayList<>(meals);
    }

    @Override
    public void addMeal(Meal meal) {
        if (meal == null) {
            throw new IllegalArgumentException("Null passed to the method");
        }
        meal.setId(mealsId.incrementAndGet());
        meals.add(meal);
    }

    @Override
    public void updateMeal(Meal meal) {
        int index = 0;
        Iterator<Meal>  it = meals.iterator();
        while (it.hasNext()){
            Meal meal1 = it.next();
            if (meal.getId() == meal1.getId()) {
                index = meals.indexOf(meal1);
                meals.remove(index);
                break;
            }
        }
        meals.add(index, meal);
    }

    @Override
    public void removeMealById(int id) {
        meals = meals.stream()
                .filter(m -> m.getId() != id)
                .collect(Collectors.toList());
    }

    @Override
    public Meal getMealById(int mealId) {
        return meals.stream()
                .filter(m -> m.getId() == mealId)
                .findFirst()
                .get();
    }
}
