package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.storage.MealStorage;
import ru.javawebinar.topjava.storage.MealStorageMap;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.TimeUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private static final int caloriesLimit = 2000;

    private MealStorage mealStorage;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        mealStorage = new MealStorageMap();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "default";
        }
        Meal meal;
        switch (action) {
            case "delete":
                mealStorage.delete(parseId(request));
                response.sendRedirect("meals");
                return;
            case "save":
                meal = new Meal();
                break;
            case "update":
                meal = mealStorage.get(parseId(request));
                break;
            default:
                log.debug("forward to meals");
                List<MealTo> mealsTo = MealsUtil.filteredByStreams(mealStorage.getAll(), caloriesLimit, m -> true);
                request.setAttribute("mealsTo", mealsTo);
                request.getRequestDispatcher("/meals.jsp").forward(request, response);
                return;
        }
        request.setAttribute("meal", meal);
        log.debug("forward to update");
        request.getRequestDispatcher("/update.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        final boolean isSaving = (id == null || id.equals("0") || id.length() ==0);
        Meal meal = new Meal(TimeUtil.parseDate(request.getParameter("DateTime")), request.getParameter("Description"),
                Integer.parseInt(request.getParameter("Calories")));

        if (isSaving) {
            mealStorage.create(meal);
        } else {
            meal.setId(parseId(request));
            mealStorage.update(meal);
        }
        response.sendRedirect("meals");
    }

    private int parseId(HttpServletRequest request) {
        return Integer.parseInt(request.getParameter("id"));
    }
}
