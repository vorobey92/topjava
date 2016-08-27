package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );
        getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed>  getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Boolean> exceedMap = getDatesWithExceeded(mealList, caloriesPerDay);
        List<UserMealWithExceed> resultList = new ArrayList<>();
        for (UserMeal meal : mealList) {
            meal.getCalories();
            LocalTime mealTime = meal.getDateTime().toLocalTime();
            if (TimeUtil.isBetween(mealTime, startTime, endTime)) {
                resultList.add(new UserMealWithExceed(meal.getDateTime(), meal.getDescription(),
                        meal.getCalories(), exceedMap.get(meal.getDateTime().toLocalDate())));
            }
        }
        return resultList;
    }

    private static Map<LocalDate, Boolean> getDatesWithExceeded(List<UserMeal> mealList, int caloriesPerDay) {
        Map<LocalDate, Boolean> exceedMap = new TreeMap<>();
        LocalDate mealDate = mealList.get(0).getDateTime().toLocalDate();
        int totalCalories = 0;
        boolean exceeded = false;
        for (UserMeal meal : mealList) {
            if (!meal.getDateTime().toLocalDate().equals(mealDate)) {
                mealDate = meal.getDateTime().toLocalDate();
                totalCalories = 0;
                exceeded = false;
            }
            totalCalories += meal.getCalories();
            if (totalCalories > caloriesPerDay) {
                exceeded = true;
            }
            exceedMap.put(mealDate, exceeded);
        }
        return exceedMap;
    }
}
