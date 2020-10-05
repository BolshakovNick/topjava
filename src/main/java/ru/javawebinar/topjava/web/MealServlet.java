package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.MealsListInit;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.storage.Storage;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private final Storage storage = MealsListInit.getStorage();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String action = request.getParameter("action");
        if (action == null) {
            log.debug("forward to meals");
            List<MealTo> mealsTo = MealsUtil.getMealsTo(storage.getAll(), MealsListInit.getCaloriesLimit());
            request.setAttribute("mealsTo", mealsTo);
            request.getRequestDispatcher("/meals.jsp").forward(request, response);
            return;
        }

        Meal meal;
        switch (action) {
            case "delete":
                storage.delete(Integer.parseInt(id));
                response.sendRedirect("meals");
                return;
            case "save":
                meal = Meal.DEFAULT_MEAL;
                break;
            case "update":
                meal = storage.get(Integer.parseInt(id));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + action);
        }
        request.setAttribute("meal", meal);
        request.getRequestDispatcher("/update.jsp").forward(request, response);
    }
}
