package ru.geekbrains.ru.geekbrains.user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/user/*")
public class UserHttpServlet extends HttpServlet {
    private UserRepository userRepository;

    @Override
    public void init() throws ServletException {
        userRepository = (UserRepository) getServletContext().getAttribute("userRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        String pathInfo = req.getPathInfo();
        pw.println("<h1>USER</h1>");
        try {
            if (pathInfo != null) {
                long id = Long.parseLong(pathInfo.substring(1));
                User user = userRepository.findById(id);
                if (user != null) {
                    pw.println("<table>");
                    pw.println("<tr>");
                    pw.println("<th>id</th>");
                    pw.println("<th>username</th>");
                    pw.println("</tr>");
                    pw.println("<tr>");
                    pw.println("<td>");
                    pw.println(user.getId());
                    pw.println("</td>");
                    pw.println("<td>");
                    pw.println(user.getUsername());
                    pw.println("</td>");
                    pw.println("</tr>");
                    pw.println("</table>");
                } else {
                    pw.println("<h3>No such user</h3>");
                }
            }
        } catch (NumberFormatException e){
            pw.println("<h3>No such user</h3>");
        }
    }
}
