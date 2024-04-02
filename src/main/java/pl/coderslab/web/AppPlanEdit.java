package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.Plan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/app/plan/edit")
public class AppPlanEdit extends HttpServlet {
    private PlanDao planDao;
    public AppPlanEdit() {
        planDao = new PlanDao();
    }
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // lepszym sposobem byłoby przesłanie id w request body?
        int planId = Integer.parseInt(req.getParameter("id"));
        String planName = req.getParameter("name");
        String planDescription = req.getParameter("description");

        Plan plan = new Plan();
        plan.setId(planId);
        plan.setName(planName);
        plan.setDescription(planDescription);

        planDao.update(plan);
    }
}
