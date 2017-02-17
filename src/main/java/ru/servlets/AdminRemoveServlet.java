package ru.servlets;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.entity.Offer;
import ru.entity.Orders;
import ru.repository.OfferRepository;
import ru.repository.OrderRepository;
import ru.specifications.OrderSpecificationByOfferId;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by ааааааааааеееееееее on 15.02.2017.
 */
@WebServlet(name = "AdminRemoveServlet")
public class AdminRemoveServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session.getAttribute("userId") != null){
            int offerId = Integer.parseInt(request.getParameter("offerId"));
            OrderSpecificationByOfferId spec = new OrderSpecificationByOfferId(offerId);
            ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
            OrderRepository rep = (OrderRepository)context.getBean("orderRepository");
            List<Orders> orders = rep.query(spec);
            if (orders.size() != 0){
                session.setAttribute("deleteError", "You can not delete this offer, because some user purchased this!");
                request.getRequestDispatcher("/admin/AdminView.jsp").include(request,response);
            } else {
                Offer deleteOffer = new Offer();
                deleteOffer.setOffer_id(offerId);
                OfferRepository rep2 = (OfferRepository)context.getBean("offerRepository");
                rep2.removeOffer(deleteOffer);
                response.sendRedirect("/adminPanel");
            }
        } else {
            response.sendRedirect("/userView");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session.getAttribute("admin") != null) {
            boolean isAdmin = (Boolean) session.getAttribute("admin");
            if (isAdmin == false) {
                response.sendRedirect("/offerView");
            } else {
                response.sendRedirect("/adminPanel");
            }
        } else {
            response.sendRedirect("/userView");
        }
    }
}
