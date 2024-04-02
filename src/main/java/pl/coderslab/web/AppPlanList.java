package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.Plan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/app/plan/list")
public class AppPlanList extends HttpServlet {
    private PlanDao planDao;
    public AppPlanList() {
        planDao = new PlanDao();
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Plan> plans = planDao.findAll();
        request.setAttribute("plans", plans);
        request.getRequestDispatcher("/WEB-INF/app-plan-list.jsp").forward(request, response);
    }
}