package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.dao.MealDaoMemoryImpl;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by ia.vorobev on 09.09.2016.
 */
public class MealServlet extends HttpServlet {

    private static final Logger LOG = getLogger(MealServlet.class);
    public static final String MEAL_LIST_JSP = "/mealList.jsp";
    public static final String ADD_MEAL_JSP = "/addMeal.jsp";

    private MealDao dao;

    public MealServlet() {
        super();
        dao = new MealDaoMemoryImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String forward = MEAL_LIST_JSP;
        String action = req.getParameter("action");

        if (action == null) {
            req.setAttribute("mealList", MealsUtil.getWithExceeded(dao.getMeals(), 2000));
        } else if (action.equalsIgnoreCase("delete")) {
            int mealId = Integer.parseInt(req.getParameter("id"));
            dao.removeMealById(mealId);
            req.setAttribute("mealList", MealsUtil.getWithExceeded(dao.getMeals(), 2000));
        } else if (action.equalsIgnoreCase("edit")) {
            forward = ADD_MEAL_JSP;
            int mealId = Integer.parseInt(req.getParameter("id"));
            Meal meal = dao.getMealById(mealId);
            req.setAttribute("meal", meal);
        } else {
            forward = ADD_MEAL_JSP;
        }

        RequestDispatcher view = req.getRequestDispatcher(forward);
        view.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String description = req.getParameter("description");
        LocalDateTime dateTime = LocalDateTime.parse(req.getParameter("dateTime"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        Integer calories = new Integer(req.getParameter("calories"));
        String mealId = req.getParameter("id");

        Meal meal = new Meal(dateTime, description, calories);
        if (mealId == null || "".equals(mealId)) {
            dao.addMeal(meal);
        } else {
            meal.setId(Integer.parseInt(mealId));
            dao.updateMeal(meal);
        }

        req.setAttribute("mealList", MealsUtil.getWithExceeded(dao.getMeals(), 2000));
        req.getRequestDispatcher(MEAL_LIST_JSP).forward(req, resp);
    }
}
