package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

import static ru.javawebinar.topjava.MealTestData.*;

/**
 * Created by ia.vorobev on 05.10.2016.
 */
@ContextConfiguration({
    "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class MealServiceTest {

    @Autowired
    private MealService mealService;

    @Autowired
    private DbPopulator dbPopulator;

    @Before
    public void setUp() {
        dbPopulator.execute();
    }

    @Test
    public void get() throws Exception {
        MATCHER.assertEquals(FIRST_ADMIN_MEAL, mealService.get(FIRST_MEAL, UserTestData.ADMIN_ID));
    }

    @Test
    public void delete() throws Exception {
        mealService.delete(FIRST_ADMIN_MEAL.getId(), UserTestData.ADMIN_ID);
        MATCHER.assertCollectionEquals(Collections.singletonList(SECOND_ADMIN_MEAL),
                                         mealService.getAll(UserTestData.ADMIN_ID));
    }

    @Test
    public void getBetweenDates() throws Exception {
        MATCHER.assertCollectionEquals(Arrays.asList(FIRST_USER_MEAL),
                mealService.getBetweenDates(LocalDate.of(2016,10,3), LocalDate.of(2016,10,4), UserTestData.USER_ID));
    }

    @Test
    public void getBetweenDateTimes() throws Exception {
        MATCHER.assertCollectionEquals(Arrays.asList(FIRST_ADMIN_MEAL),
                mealService.getBetweenDateTimes(LocalDateTime.of(2016,10,5,9,0), LocalDateTime.of(2016,10,5,11,0), UserTestData.ADMIN_ID));
    }

    @Test
    public void getAll() throws Exception {
        MATCHER.assertCollectionEquals(Arrays.asList(SECOND_USER_MEAL, FIRST_USER_MEAL),
                                        mealService.getAll(UserTestData.USER_ID));
    }

    @Test
    public void update() throws Exception {
        Meal updatedMeal = new Meal(FIRST_MEAL, LocalDateTime.now(), "new description", 1);
        mealService.update(updatedMeal, UserTestData.ADMIN_ID);
        MATCHER.assertEquals(updatedMeal, mealService.get(FIRST_MEAL, UserTestData.ADMIN_ID));
    }

    @Test
    public void save() throws Exception {
        Meal updatedMeal = new Meal(LocalDateTime.now(), "new description", 1);
        int id = mealService.save(updatedMeal, UserTestData.ADMIN_ID).getId();
        MATCHER.assertEquals(updatedMeal, mealService.get(id, UserTestData.ADMIN_ID));
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundGet() {
        mealService.get(FIRST_MEAL, UserTestData.USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundUpdate() {
        mealService.update(FIRST_ADMIN_MEAL, UserTestData.USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundDelete() {
        mealService.delete(FIRST_MEAL, UserTestData.USER_ID);
    }
}