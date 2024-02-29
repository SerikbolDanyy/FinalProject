package Servlet;

import Connector.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.http.HttpClient;

@WebServlet(value = "/profile")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("CurrentUser");
        if (user!=null) {
            req.getRequestDispatcher("profile.jsp").forward(req, resp);
        }else {
            req.getRequestDispatcher("notFound.jsp").forward(req, resp);
        }
    }
}
