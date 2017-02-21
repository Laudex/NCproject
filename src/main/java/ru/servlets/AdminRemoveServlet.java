package ru.servlets;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.entity.AttrValues;
import ru.entity.Offer;
import ru.entity.Orders;
import ru.repository.AttrValuesRepository;
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
                AttrValues attrValues = new AttrValues(offerId);
                AttrValuesRepository rep2 = (AttrValuesRepository) context.getBean("attrValRepository");
                rep2.removeAttrValues(attrValues);
                Offer deleteOffer = new Offer();
                deleteOffer.setOffer_id(offerId);
                OfferRepository rep3 = (OfferRepository)context.getBean("offerRepository");
                rep3.removeOffer(deleteOffer);
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
            if (!isAdmin) {
                response.sendRedirect("/offerView");
            } else {
                response.sendRedirect("/adminPanel");
            }
        } else {
            response.sendRedirect("/userView");
        }
    }
}
