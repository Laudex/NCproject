package ru.servlets;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.entity.Attr;
import ru.entity.AttrValues;
import ru.entity.Offer;
import ru.repository.AttrRepository;
import ru.repository.AttrValuesRepository;
import ru.repository.OfferRepository;
import ru.specifications.AttrValuesSpecificationByOfferId;
import ru.specifications.EmptySpecification;

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

/**
 * Created by ааааааааааеееееееее on 09.03.2017.
 */
@WebServlet(name = "OfferInfoServlet")
public class OfferInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session.getAttribute("userId") != null){
            int offerId = Integer.parseInt( request.getParameter("offerId"));
            ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
            String query = String.format("WHERE offer_id = %s", offerId);
            EmptySpecification spec = new EmptySpecification(query);
            OfferRepository rep = (OfferRepository) context.getBean("offerRepository");
            List<Offer> offerList = rep.query(spec);
            String offerName = offerList.get(0).getName();
            AttrValuesRepository rep2 = (AttrValuesRepository)context.getBean("attrValRepository");
            AttrRepository attrRep = (AttrRepository)context.getBean("attrRepository");
            AttrValuesSpecificationByOfferId spec2 = new AttrValuesSpecificationByOfferId(offerId);
            List<AttrValues> attrValuesList = rep2.query(spec2);
            List<String> attrNames = new ArrayList<>();
            List<String> attrValues = new ArrayList<>();
            for (Iterator<AttrValues> iterator = attrValuesList.iterator(); iterator.hasNext(); ){
                AttrValues nextAttrValues = iterator.next();
                int attrId = nextAttrValues.getAttrId();
                String query2 = String.format("WHERE attr_id = %s", attrId);
                EmptySpecification spec3 = new EmptySpecification(query2);
                List<Attr> attrList =  attrRep.query(spec3);
                String attrName = attrList.get(0).getName();
                String attrValue = nextAttrValues.getValue();
                attrNames.add(attrName);
                attrValues.add(attrValue);
            }
            session.setAttribute("offerName",offerName);
            session.setAttribute("attrNames",attrNames);
            session.setAttribute("attrValues",attrValues);
            request.getRequestDispatcher("/user/OfferInfo.jsp").include(request, response);

        } else {
            response.sendRedirect("/userView");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
