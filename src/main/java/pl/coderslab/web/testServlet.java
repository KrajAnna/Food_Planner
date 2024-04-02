package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.dao.BookDao;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@WebServlet("/test")
public class testServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PlanDao planDao = new PlanDao();
        Map< String, List<LastPlan>> lastPlans =  planDao.findLastPlan(1);

        //getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);

        Set<String> keys = lastPlans.keySet();
        for(String key : keys) {
            response.getWriter().println(key);
            List <LastPlan> listRecipesForDay = lastPlans.get(key);
            for (LastPlan recipeForDay : listRecipesForDay){
                response.getWriter().println(recipeForDay.getMealName());
            }
        }


        LocalDateTime createdDate = LocalDateTime.of(2023, Month.MAY, 5, 0, 0);
        Plan plan = new Plan("Plan", "Opis",createdDate,  29);

        planDao.create(plan);
        System.out.println(plan);

        Book book = new Book("Tytuł", "Autor", "1235");
        BookDao bookDao = new BookDao();

        bookDao.create(book);



        //Recipe recipe = new Recipe();


//    public Plan(int id, String name, String description, LocalDateTime created, int adminId) {

    }

}
