package ru.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "UserViewServlet")
public class UserViewServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        if (session.getAttribute("admin") != null) {
            boolean isAdmin = (Boolean) session.getAttribute("admin");
            if (!isAdmin) {
                response.sendRedirect("/offerView");
            } else {
                response.sendRedirect("/adminPanel");
            }
        } else {
            request.getRequestDispatcher("user/UserView.jsp").include(request,response);
        }
    }
}
