package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.util.UserMealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by roman on 05.07.2016.
 */
public class MealServlet extends HttpServlet{
private static final Logger LOG = getLogger(MealServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info("melList");
        req.setAttribute("meallist", UserMealsUtil.getWithExceeded(UserMealsUtil.MEAL_LIST, UserMealsUtil.DEFAULT_CALORIES_PER_DAY));
        getServletContext().getRequestDispatcher("/mealList.jsp").forward(req,resp);

    }
}
