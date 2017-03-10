package ru.servlets;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.entity.Offer;
import ru.entity.Orders;
import ru.repository.OfferRepository;
import ru.repository.OrderRepository;
import ru.specifications.EmptySpecification;
import ru.specifications.OrderSpecificationByOfferId;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@WebServlet(name = "AdminPanelServlet")
public class AdminPanelServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        boolean isAdmin = (Boolean) session.getAttribute("admin");
        if (!isAdmin){
            response.sendRedirect("/offerView");
        } else {
            EmptySpecification spec = new EmptySpecification();
            ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
            OfferRepository rep = (OfferRepository) context.getBean("offerRepository");
            List<Offer> offerList = rep.query(spec);
            Map<Offer,Boolean> offerMap = new LinkedHashMap<>();
            Iterator<Offer> iter = offerList.iterator();
            while(iter.hasNext()){
                Offer nextOffer = iter.next();
                int offerId = nextOffer.getOfferId();
                OrderSpecificationByOfferId spec2 = new OrderSpecificationByOfferId(offerId);
                OrderRepository rep2 = (OrderRepository)context.getBean("orderRepository");
                List<Orders> orders = rep2.query(spec2);
                if (orders.size() != 0){
                    offerMap.put(nextOffer,false);
                } else {
                    offerMap.put(nextOffer,true);
                }
            }
            session.setAttribute("offers",offerMap);
            request.getRequestDispatcher("/admin/AdminView.jsp").include(request,response);
        }
    }
}
