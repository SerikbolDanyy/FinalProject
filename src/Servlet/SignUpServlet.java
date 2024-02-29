package Servlet;

import Database.UserDB;
import com.sun.net.httpserver.HttpExchange;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/signUp")
public class SignUpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("signUp.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("emailAddress");
        String password = req.getParameter("password");
        String fullName = req.getParameter("fullName");
        resp.sendRedirect("/FinalProject_Web_exploded/login");
        UserDB.addUser(email, password, fullName);

    }
}
