package Servlet;

import Connector.User;
import Database.UserDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(value = "/addPost")
public class AddPostServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("CurrentUser");
        if (user!=null) {
            req.getRequestDispatcher("addPost.jsp").forward(req, resp);
        }else{
            req.getRequestDispatcher("notFound.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        User user = (User) req.getSession().getAttribute("CurrentUser");
        long userId = user.getId();
        resp.sendRedirect("/FinalProject_Web_exploded/profile");
        try {
            UserDB.addPost(userId, title, content);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
