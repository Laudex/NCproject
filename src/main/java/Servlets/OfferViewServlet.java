package Servlets;

import Entity.Offer;
import Entity.Orders;
import Entity.User;
import Repository.OfferRepository;
import Repository.OrderRepository;
import Repository.UserRepository;
import Specifications.EmptySpecification;
import Specifications.OrderSpecificationByUserId;
import Specifications.UserSpecificationByName;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@WebServlet(name = "OfferViewServlet")
public class OfferViewServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        UserSpecificationByName spec = new UserSpecificationByName(name);
        UserRepository rep = new UserRepository();
        try {
            List<User> listUsers = rep.query(spec);
            int id = listUsers.get(0).getUserId();
            OrderSpecificationByUserId spec2 = new OrderSpecificationByUserId(id);
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
            request.setAttribute("list",listOffers);
            request.setAttribute("name",name);
            request.setAttribute("error","nope");
            RequestDispatcher dispatcher = request.getRequestDispatcher("user/OfferView.jsp");
            dispatcher.forward(request,response);
        } catch(IndexOutOfBoundsException e){
            String error = "This name was not found! Try again!";
            request.setAttribute("error",error);
            RequestDispatcher dispatcher = request.getRequestDispatcher("user/OfferView.jsp");
            dispatcher.forward(request,response);
        }

    }

    /*protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }*/
}
