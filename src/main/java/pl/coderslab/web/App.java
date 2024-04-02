package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.LastPlan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/app")
public class App extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //wyciagniecie danych z  sesji
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin");

        // metody do wyswietlenia danych na stronie glownej app
        PlanDao planDao = new PlanDao();
        int qtyPlans = planDao.findAllPlansRecipes(admin.getId());
        request.setAttribute("qtyPlans", qtyPlans);

        RecipeDao recipeDao = new RecipeDao();
        int qtyRecipes = recipeDao.findAllUserRecipes(admin.getId());
        request.setAttribute("qtyRecipes", qtyRecipes);

        Map<String, List<LastPlan>> lastPlan = planDao.findLastPlan(admin.getId());
        request.setAttribute("lastPlan", lastPlan);
        getServletContext().getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(request, response);
    }
}
