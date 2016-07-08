package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.InMemoryRepository;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.UserMealsUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by roman on 05.07.2016.
 */
public class MealServlet extends HttpServlet{
private static final Logger LOG = getLogger(MealServlet.class);
private UserMealRepository storage;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = new InMemoryRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        if (action == null)
        {
            LOG.info("getAll");
            req.setAttribute("meallist", UserMealsUtil.getWithExceeded(storage.getAll(), UserMealsUtil.DEFAULT_CALORIES_PER_DAY));
            getServletContext().getRequestDispatcher("/mealList.jsp").forward(req,resp);

        }else if(action.equals("delete"))
        {

            int id = getId(req);
            LOG.info("Delete {}",id);
            storage.delete(id);
            resp.sendRedirect("meals");
        }
        else
        {
            LOG.info("create");
            final UserMeal meal =  action.equals("create") ?
            new UserMeal(LocalDateTime.now(),"descript",1000) :
                    storage.get( getId(req));
            req.setAttribute("meal",meal);
            req.getRequestDispatcher("mealEdit.jsp").forward(req,resp);


        }

    }

    private int getId(HttpServletRequest req)
    {
        String paramId = Objects.requireNonNull(req.getParameter("id"));
        LOG.info("paramId {}",paramId);
        int id = Integer.valueOf(paramId);
        return id;
    }
}
