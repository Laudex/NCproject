package ru.servlets;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.entity.Offer;
import ru.entity.Orders;
import ru.repository.OfferRepository;
import ru.repository.OrderRepository;
import ru.specifications.EmptySpecification;
import ru.specifications.OrderSpecificationByUserId;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@WebServlet(name = "OfferRemServlet")
public class OfferRemServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session.getAttribute("userId") != null) {
            ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
            int offerId = Integer.parseInt(request.getParameter("offerId"));
            int userId = (Integer) session.getAttribute("userId");
            String query = String.format("WHERE user_id = %s AND offer_id = %s", userId, offerId);
            EmptySpecification spec = new EmptySpecification(query);
            OrderRepository rep = (OrderRepository) context.getBean("orderRepository");
            List<Orders> orders = rep.query(spec);
            int orderId = orders.get(0).getOrderId();
            String startDate = orders.get(0).getStartDate();
            Orders newOrder = new Orders(orderId, userId, offerId, startDate);
            rep.removeOrders(newOrder);
            OrderSpecificationByUserId spec2 = new OrderSpecificationByUserId(userId);
            List<Orders> listOrders = rep.query(spec2);
            OfferRepository rep3 = (OfferRepository) context.getBean("offerRepository");
            List<Offer> listOffers = new ArrayList<Offer>();
            for (Iterator<Orders> i = listOrders.iterator(); i.hasNext(); ) {
                String query2 = String.format("WHERE offer_id = %s", i.next().getOfferId());
                EmptySpecification spec3 = new EmptySpecification(query2);
                List<Offer> list = rep3.query(spec3);
                listOffers.add(list.get(0));
            }
            session.setAttribute("list", listOffers);
            request.getRequestDispatcher("/user/OfferView.jsp").include(request, response);
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
