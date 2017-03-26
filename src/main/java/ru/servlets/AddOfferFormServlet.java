package ru.servlets;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.entity.AttrValues;
import ru.entity.Offer;
import ru.repository.AttrValuesRepository;
import ru.repository.OfferRepository;
import ru.specifications.OfferSpecificationByName;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by ааааааааааеееееееее on 26.03.2017.
 */
@WebServlet(name = "AddOfferFormServlet")
public class AddOfferFormServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String name = request.getParameter("offerName");
        String type = request.getParameter("offerType");
        String traffic = request.getParameter("offerTraffic");
        String price = request.getParameter("offerPrice");
        String period = request.getParameter("offerPeriod");
        if (name.isEmpty() || type.isEmpty() || traffic.isEmpty() || price.isEmpty() || period.isEmpty()) {
            session.setAttribute("error", "Some parameter(s) is empty. Fill it!");
            response.sendRedirect("/addOfferForm");
        } else {
            Offer newOffer = new Offer(name);
            ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
            OfferRepository rep = (OfferRepository) context.getBean("offerRepository");
            OfferSpecificationByName spec = new OfferSpecificationByName(name);
            try {
                List<Offer> offerList = rep.query(spec);
                int offerId = offerList.get(0).getOfferId();
                session.setAttribute("error", "This offer is already exist!");
                response.sendRedirect("/addOfferForm");
            } catch (IndexOutOfBoundsException e) {
                rep.addOffer(newOffer);
                List<Offer> offerList = rep.query(spec);
                int offerId = offerList.get(0).getOfferId();
                AttrValuesRepository rep2 = (AttrValuesRepository)context.getBean("attrValRepository");
                AttrValues attrValues1 = new AttrValues(offerId,1,type);
                AttrValues attrValues2 = new AttrValues(offerId,2,traffic);
                AttrValues attrValues3 = new AttrValues(offerId,3,price);
                AttrValues attrValues4 = new AttrValues(offerId,4,period);
                rep2.addAttrValues(attrValues1);
                rep2.addAttrValues(attrValues2);
                rep2.addAttrValues(attrValues3);
                rep2.addAttrValues(attrValues4);
                session.setAttribute("success", "New Offer was added successfully!");
                response.sendRedirect("/adminPanel");
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session.getAttribute("admin") != null) {
            boolean isAdmin = (Boolean) session.getAttribute("admin");
            if (!isAdmin) {
                response.sendRedirect("/offerView");
            } else {
                request.getRequestDispatcher("/admin/AddOfferForm.jsp").include(request, response);
            }
        } else {
            response.sendRedirect("/userView");
        }
    }
}
