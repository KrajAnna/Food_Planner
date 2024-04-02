package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Plan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/app/plan/add")
public class AddScheduleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/app-add-schedules.jsp").forward(req, resp);

        HttpSession session = req.getSession();
        Integer adminId = (Integer) session.getAttribute("adminId");
        if (adminId == null) {
            AdminDao adminDao = new AdminDao();
            String email = (String) session.getAttribute("email"); // Pobierz email z sesji, który może być użyty do wyszukania admina w bazie danych
            String password = (String) session.getAttribute("password"); // Pobierz hasło z sesji, które może być użyte do wyszukania admina w bazie danych
            Admin admin1 = adminDao.checkAuthorization(email, password); // Metoda zwracająca id admina na podstawie emaila i hasła, zmień ją zgodnie z twoją implementacją

            adminId =admin1.getId();
            if (adminId != null) {
                session.setAttribute("adminId", adminId);
            } else {
                resp.sendRedirect(req.getContextPath() + "/login");
                return;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String planName = req.getParameter("planName");
        String planDesc = req.getParameter("planDescription");
        LocalDateTime created = LocalDateTime.now();
        HttpSession session = req.getSession();
        Admin admin = (Admin) session.getAttribute("admin");
        //Integer adminId = (Integer) session.getAttribute("adminId");
        Integer adminId=admin.getId();
        if (adminId == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        Plan plan = new Plan();
        plan.setName(planName);
        plan.setDescription(planDesc);
        plan.setCreated(created);
        plan.setAdminId(adminId);
        PlanDao planDao = new PlanDao();
        planDao.create(plan);
        resp.sendRedirect(req.getContextPath() + "/app/plan/list");

    }
}
