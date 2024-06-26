package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private AdminDao adminDao;
    public RegisterServlet() {
        adminDao = new AdminDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(getServletContext().getContextPath());
        getServletContext().getRequestDispatcher("/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        System.out.println(name);
        System.out.println(surname);
        System.out.println(email);
        System.out.println(password);

        Admin user = new Admin();
        user.setFirstName(name);
        user.setLastName(surname);
        user.setEmail(email);
        user.setPassword(password);
        user.setSuperadmin(false);
        user.setEnable(true);

        user = adminDao.create(user); // być może powinien zostać zwrócony?
        resp.sendRedirect("/login");
    }
}
