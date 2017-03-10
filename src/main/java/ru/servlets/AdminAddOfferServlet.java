package ru.servlets;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import ru.entity.AttrValues;
import ru.entity.Offer;
import ru.repository.AttrValuesRepository;
import ru.repository.OfferRepository;
import ru.specifications.OfferSpecificationByName;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;


@MultipartConfig
@WebServlet(name = "AdminAddOfferServlet")
public class AdminAddOfferServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part filePart = request.getPart("offer");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        InputStream fileContent = filePart.getInputStream();
        String file = new Scanner(fileContent,"UTF-8").useDelimiter("\\A").next();
        try {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
                    .parse(new InputSource(new StringReader(file)));
            Element root = doc.getDocumentElement();
            NodeList offers = doc.getElementsByTagName("offer");
            for (int i = 0; i < offers.getLength(); i++){
                Node offer = offers.item(i);
                Element eOffer = (Element) offer;
                String offerName = eOffer.getAttribute("name");
                Offer newOffer = new Offer(offerName);
                ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
                OfferRepository rep = (OfferRepository) context.getBean("offerRepository");
                rep.addOffer(newOffer);
                OfferSpecificationByName spec = new OfferSpecificationByName(offerName);
                List<Offer> offerList= rep.query(spec);
                int offerId = offerList.get(0).getOfferId();
                NodeList attrs = eOffer.getElementsByTagName("attr");
                for (int j = 0; j < attrs.getLength(); j++){
                    Node attr = attrs.item(j);
                    Element eAttr = (Element) attr;
                    int attrId = Integer.parseInt(eAttr.getAttribute("id"));
                    String attrValue = eAttr.getTextContent();
                    AttrValuesRepository rep2 = (AttrValuesRepository)context.getBean("attrValRepository");
                    AttrValues attrValues = new AttrValues(offerId,attrId,attrValue);
                    rep2.addAttrValues(attrValues);
                }
                response.sendRedirect("/adminPanel");
            }
        } catch (SAXException | ParserConfigurationException e) {
            request.setAttribute("error","Error in parse xml file!");
            response.sendRedirect("/adminPanel");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session.getAttribute("admin") != null) {
            boolean isAdmin = (Boolean) session.getAttribute("admin");
            if (!isAdmin) {
                response.sendRedirect("/offerView");
            } else {
                request.getRequestDispatcher("/admin/AddOffer.jsp").include(request, response);
            }
        } else {
            response.sendRedirect("/userView");
        }

    }
}
