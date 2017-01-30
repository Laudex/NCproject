import ru.dbclasses.DBConnectionFactory;
import ru.entity.Offer;
import org.junit.Test;
import ru.repository.OfferRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.Assert.*;


public class OfferRepositoryTest {
    @Test
    public void addOffer() throws Exception {
        Offer offer = new Offer(6,"Offer 6");
        OfferRepository rep = new OfferRepository();
        rep.addOffer(offer);
        Offer testOffer = new Offer();
        String sqlQuery = String.format("SELECT * FROM offer WHERE offer_id = %s",offer.getOfferId());
        Connection connection;
        Statement stmt;
        try {
            connection = DBConnectionFactory.conFactory();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);
            if(rs.next()){
                testOffer.setOffer_id(rs.getInt("offer_id"));
                testOffer.setName(rs.getString("name"));
            }
            rs.close();
            stmt.close();
            connection.close();
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
        assertEquals(offer.getOfferId(),testOffer.getOfferId());
        assertEquals(offer.getName(),testOffer.getName());
    }

    @Test
    public void removeOffer() throws Exception {
        Offer offer = new Offer(6,"Offer 6");
        OfferRepository rep = new OfferRepository();
        rep.removeOffer(offer);
        Offer testOffer = null;
        String sqlQuery = String.format("SELECT * FROM offer WHERE offer_id = %s",offer.getOfferId());
        Connection connection;
        Statement stmt;
        try {
            connection = DBConnectionFactory.conFactory();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);
            if(rs.next()){
                testOffer = new Offer();
                testOffer.setOffer_id(rs.getInt("offer_id"));
                testOffer.setName(rs.getString("name"));
            }
            rs.close();
            stmt.close();
            connection.close();
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
        assertNull(testOffer);
    }

    @Test
    public void updateOffer() throws Exception {
        Offer offer = new Offer(5,"test");
        OfferRepository rep = new OfferRepository();
        rep.updateOffer(offer);
        Offer testOffer = new Offer();
        String sqlQuery = String.format("SELECT * FROM offer WHERE offer_id = %s",offer.getOfferId());
        Connection connection;
        Statement stmt;
        try {
            connection = DBConnectionFactory.conFactory();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);
            if(rs.next()){
                testOffer.setOffer_id(rs.getInt("offer_id"));
                testOffer.setName(rs.getString("name"));
            }
            rs.close();
            stmt.close();
            connection.close();
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
        assertEquals(offer.getOfferId(),testOffer.getOfferId());
        assertEquals(offer.getName(),testOffer.getName());
    }

}