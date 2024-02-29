package Servlet;

import Connector.User;
import Database.UserDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("emailAddress");
        String password = req.getParameter("password");
        HttpSession httpSession = req.getSession();

        User user = UserDB.getUser(email);
        if (user!=null && user.getPassword().equals(password)){
            resp.sendRedirect("profile.jsp");
            httpSession.setAttribute("CurrentUser", user);
        }else{
            resp.sendRedirect("/login?notFoundUser");
        }
    }
}
