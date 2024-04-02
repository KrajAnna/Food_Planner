package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.Plan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/app/plan/delete")
public class DeleteScheduleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String planId = req.getParameter("id");
        req.setAttribute("planId", planId);
        getServletContext().getRequestDispatcher("/WEB-INF/app-delete-schedules.jsp").forward(req, resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String planIdParam = req.getParameter("planId");
        if (planIdParam != null && !planIdParam.isEmpty()) {
            try {
                int planId = Integer.parseInt(planIdParam);
                PlanDao planDao = new PlanDao();
                planDao.delete(planId);
                resp.sendRedirect(req.getContextPath() + "/app/plan/list");
            } catch (NumberFormatException e) {
                e.printStackTrace();
                resp.sendRedirect(req.getContextPath() + "/app/plan/error"); // Załóżmy, że "/app/plan/error" to strona błędu
            }
        } else {
            resp.sendRedirect(req.getContextPath() + "/app/plan/list");
        }
    }

}
