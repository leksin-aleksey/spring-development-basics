package ru.geekbrains.ru.geekbrains.servlets;

import ru.geekbrains.ru.geekbrains.user.User;
import ru.geekbrains.ru.geekbrains.user.UserRepository;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/users")
public class UsersHttpServlet extends HttpServlet {
    private UserRepository userRepository;

    @Override
    public void init() throws ServletException {
        userRepository = (UserRepository) getServletContext().getAttribute("userRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
//        resp.getWriter().println("<h1>" + "TEST" + "</h1>");

//        resp.getWriter().println("<h1>" + (userRepository == null ? "null" : "not_null") + "</h1>");
//        resp.getWriter().println("<h1>" + userRepository.findById(1L).toString() + "</h1>");
        pw.println("<h1>USERS</h1>");
        pw.println("<table>");
        pw.println("<tr>");
        pw.println("<th>id</th>");
        pw.println("<th>username</th>");
        pw.println("</tr>");
        List<User> users = userRepository.findAll();
        for (User user : users){
            pw.println("<tr>");
            pw.println("<td>");
            pw.println(user.getId());
            pw.println("</td>");
            pw.println("<td>");
            pw.println(user.getUsername());
            pw.println("</td>");
            pw.println("</tr>");
        }
        pw.println("</table>");
    }
}
