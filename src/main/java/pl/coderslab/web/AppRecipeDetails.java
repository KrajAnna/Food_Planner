package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet("/app/recipe/details")
public class AppRecipeDetails extends HttpServlet {
    private RecipeDao recipeDao;

    public AppRecipeDetails() {
        recipeDao = new RecipeDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int recipeId = Integer.parseInt(req.getParameter("id"));
        Recipe recipe = recipeDao.getRecipe(recipeId);
        if (recipe == null) {
            resp.sendRedirect("/WEB-INF/app/recipe");
            return;
        }
        req.setAttribute("recipe", recipe);
        req.getServletContext().getRequestDispatcher("/WEB-INF/app-recipe-details.jsp").forward(req, resp);
    }
}
