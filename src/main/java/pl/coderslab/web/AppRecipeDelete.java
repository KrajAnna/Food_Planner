package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/app/recipe/delete")
public class AppRecipeDelete extends HttpServlet {
    private RecipeDao recipeDao;
    public AppRecipeDelete() {
        recipeDao = new RecipeDao();
    }
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // lepszym sposobem byłoby przesłanie id w request body?
        int recipeId = Integer.parseInt(req.getParameter("id"));
        recipeDao.delete(recipeId);
    }
}
