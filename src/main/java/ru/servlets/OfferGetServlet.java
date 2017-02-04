package ru.servlets;

import ru.entity.Offer;
import ru.entity.Orders;
import ru.repository.OfferRepository;
import ru.repository.OrderRepository;
import ru.specifications.EmptySpecification;
import ru.specifications.OrderSpecificationByUserId;

import javax.persistence.criteria.Order;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


@WebServlet(name = "OfferGetServlet")
public class OfferGetServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session.getAttribute("userId") != null) {
            PrintWriter out = response.getWriter();
            int offerId = Integer.parseInt(request.getParameter("offerId"));
            int userId = (Integer)session.getAttribute("userId");
            Date dNow = new Date();
            SimpleDateFormat ft =  new SimpleDateFormat("yyyy-MM-dd");
            //out.print(ft.format(dNow));
            Orders newOrder = new Orders(userId,offerId,ft.format(dNow));
            OrderRepository rep = new OrderRepository();
            rep.addOrders(newOrder);
            OrderSpecificationByUserId spec2 = new OrderSpecificationByUserId(userId);
            OrderRepository rep2 = new OrderRepository();
            List<Orders> listOrders = rep2.query(spec2);
            OfferRepository rep3 = new OfferRepository();
            List<Offer> listOffers = new ArrayList<Offer>();
            for(Iterator<Orders> i = listOrders.iterator(); i.hasNext();) {
                String query = String.format("WHERE offer_id = %s", i.next().getOfferId());
                EmptySpecification spec3 = new EmptySpecification(query);
                List<Offer> list = rep3.query(spec3);
                listOffers.add(list.get(0));
            }
            session.setAttribute("list",listOffers);
            request.getRequestDispatcher("/user/OfferView.jsp").include(request,response);
        } else {
            response.sendRedirect("/userView");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session.getAttribute("userId") !=null){
            request.getRequestDispatcher("/user/OfferView.jsp").include(request,response);
        } else {
            response.sendRedirect("/userView");
        }
    }
}
