package ru.servlets;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Created by ааааааааааеееееееее on 17.02.2017.
 */
@WebServlet(name = "AdminAddOfferServlet")
public class AdminAddOfferServlet extends HttpServlet {
    private Random random = new Random();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session.getAttribute("admin") != null) {
            boolean isAdmin = (Boolean) session.getAttribute("admin");
            if (isAdmin == false) {
                response.sendRedirect("/offerView");
            } else {
                request.getRequestDispatcher("/admin/AddOffer.jsp").include(request, response);
            }
        } else {
            response.sendRedirect("/userView");
        }

    }
}
