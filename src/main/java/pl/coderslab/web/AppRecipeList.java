package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/app/recipe/list")
public class AppRecipeList extends HttpServlet {
    private RecipeDao recipeDao;
    public AppRecipeList() {
        recipeDao = new RecipeDao();
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Recipe> recipes = recipeDao.findAll();
        request.setAttribute("recipes", recipes);
        getServletContext().getRequestDispatcher("/WEB-INF/app-recipe-list.jsp").forward(request, response);
    }
}