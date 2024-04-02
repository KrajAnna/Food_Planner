package pl.coderslab.web;

import pl.coderslab.dao.DayNameDao;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.dao.RecipePlanDao;
import pl.coderslab.model.DayName;
import pl.coderslab.model.Plan;
import pl.coderslab.model.Recipe;
import pl.coderslab.model.RecipePlan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/app/recipe/plan/add")
public class AppRecipePlanAdd extends HttpServlet {
    private PlanDao planDao;
    private RecipeDao recipeDao;
    private DayNameDao dayNameDao;
    private RecipePlanDao recipePlanDao;

    public AppRecipePlanAdd() {
        planDao = new PlanDao();
        recipeDao = new RecipeDao();
        dayNameDao = new DayNameDao();
        recipePlanDao = new RecipePlanDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Plan> plans = planDao.findAll();
        List<Recipe> recipes = recipeDao.findAll();
        List<DayName> days = dayNameDao.findAll();
        req.setAttribute("plans", plans);
        req.setAttribute("recipes", recipes);
        req.setAttribute("days", days);
        req.getServletContext().getRequestDispatcher("/WEB-INF/app-recipe-plan-add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int planId = Integer.parseInt(req.getParameter("planId"));
        String mealType = req.getParameter("mealType");
        int mealOrder = Integer.parseInt(req.getParameter("mealOrder"));
        int recipeId = Integer.parseInt(req.getParameter("recipeId"));
        int dayId = Integer.parseInt(req.getParameter("dayId"));

        RecipePlan recipePlan = new RecipePlan(recipeId, mealType, mealOrder, dayId, planId);
        recipePlanDao.create(recipePlan);
    }
}
