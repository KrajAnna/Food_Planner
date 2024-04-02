package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet("/app/recipe/add")
public class AddRecipeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/WEB-INF/app-add-recipe.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Admin admin = (Admin) session.getAttribute("admin");

        if (admin == null) {
            resp.sendRedirect("/login");
            return;
        }

        int adminId = admin.getId();

        String recipeName = req.getParameter("recipeName");
        String recipeDescription = req.getParameter("recipeDescription");
        int preparationTime = Integer.parseInt(req.getParameter("preparationTime"));
        String preparation = req.getParameter("preparation");
        String ingredients = req.getParameter("ingredients");
        List<String> ingredientsList  = new ArrayList<String>(Arrays.asList(ingredients.split(" , ")));
        LocalDateTime created = LocalDateTime.now();
        LocalDateTime updated = LocalDateTime.now();

        Recipe recipe=new Recipe(recipeName,ingredientsList,recipeDescription,created,updated,preparationTime,preparation,adminId);
        RecipeDao recipeDao=new RecipeDao();
        recipeDao.create(recipe);
        resp.sendRedirect(req.getContextPath() + "/app/recipe/list");
    }
}
