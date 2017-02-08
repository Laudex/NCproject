package ru.servlets;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import ru.entity.Offer;
import ru.entity.Orders;
import ru.entity.User;
import ru.repository.OfferRepository;
import ru.repository.OrderRepository;
import ru.repository.UserRepository;
import ru.specifications.EmptySpecification;
import ru.specifications.OrderSpecificationByUserId;
import ru.specifications.UserSpecificationByName;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@WebServlet(name = "OfferViewServlet")
public class OfferViewServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        UserSpecificationByName spec = new UserSpecificationByName(name);
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        UserRepository rep = (UserRepository) context.getBean("userRepository");
        try {
            List<User> listUsers = rep.query(spec);
            int id = listUsers.get(0).getUserId();
            OrderSpecificationByUserId spec2 = new OrderSpecificationByUserId(id);
            OrderRepository rep2 = (OrderRepository) context.getBean("orderRepository");
            List<Orders> listOrders = rep2.query(spec2);
            OfferRepository rep3 = (OfferRepository) context.getBean("offerRepository");
            List<Offer> listOffers = new ArrayList<Offer>();
            for (Iterator<Orders> i = listOrders.iterator(); i.hasNext(); ) {
                String query = String.format("WHERE offer_id = %s", i.next().getOfferId());
                EmptySpecification spec3 = new EmptySpecification(query);
                List<Offer> list = rep3.query(spec3);
                listOffers.add(list.get(0));
            }
            HttpSession session = request.getSession();
            session.setMaxInactiveInterval(15 * 60);
            session.setAttribute("userId", id);
            session.setAttribute("userName", name);
            session.setAttribute("list", listOffers);
            //request.setAttribute("list",listOffers);
            //request.setAttribute("name",name);
            //request.setAttribute("userId",id);
            request.getRequestDispatcher("/user/OfferView.jsp").include(request, response);
            //RequestDispatcher dispatcher = request.getRequestDispatcher("user/OfferView.jsp");
            // dispatcher.forward(request,response);
        } catch (IndexOutOfBoundsException e) {
            request.setAttribute("enterError", "This name was not found! Try again!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/user/UserView.jsp");
            dispatcher.forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession(false);
        PrintWriter out = response.getWriter();
        if (session.getAttribute("userId") != null) {
            request.getRequestDispatcher("/user/OfferView.jsp").include(request, response);
        } else {
            response.sendRedirect("/userView");
        }
    }
}
