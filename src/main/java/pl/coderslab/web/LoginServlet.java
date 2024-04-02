package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        AdminDao adminDao = new AdminDao();
        Admin admin = adminDao.checkAuthorization(email,password);
        HttpSession session = req.getSession();

        if (admin != null) {
            session.setAttribute("admin", admin);
            resp.sendRedirect(req.getContextPath() + "/app");
        }
        else {

            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
}
