package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.Plan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/app/plan/details")
public class AppScheduleDetails extends HttpServlet {
    public PlanDao planDao;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int planId = Integer.parseInt(req.getParameter("id"));
        Plan plan = new Plan();
        plan = planDao.read(planId);

        if (plan == null) {
            resp.sendRedirect("/WEB-INF/app/plan");
            return;
        }
        req.setAttribute("plan", plan);
        req.getServletContext().getRequestDispatcher("/WEB-INF/app-recipe-details.jsp").forward(req, resp);

    }
}
