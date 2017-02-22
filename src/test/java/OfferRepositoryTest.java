import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.entity.Offer;
import org.junit.Test;
import ru.repository.OfferRepository;
import ru.specifications.EmptySpecification;
import java.util.List;
import static org.junit.Assert.*;


public class OfferRepositoryTest {
    ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
    @Test
    public void addOffer() throws Exception {
        Offer offer = new Offer(200,"Offer 6");
        OfferRepository rep = (OfferRepository)context.getBean("offerRepository");
        rep.addOffer(offer);
        String sqlQuery = String.format("WHERE offer_id = %s",offer.getOfferId());
        EmptySpecification spec = new EmptySpecification(sqlQuery);
        List<Offer> testOffer = rep.query(spec);
        assertEquals(offer.getOfferId(),testOffer.get(0).getOfferId());
        assertEquals(offer.getName(),testOffer.get(0).getName());
    }

    @Test
    public void removeOffer() throws Exception {
        Offer offer = new Offer(200,"Offer 6");
        OfferRepository rep = (OfferRepository)context.getBean("offerRepository");
        rep.removeOffer(offer);
        String sqlQuery = String.format("WHERE offer_id = %s",offer.getOfferId());
        EmptySpecification spec = new EmptySpecification(sqlQuery);
        List<Offer> testOffer = rep.query(spec);
        assertEquals(testOffer.size(),0);
    }

    @Test
    public void updateOffer() throws Exception {
        Offer offer = new Offer(8,"test");
        OfferRepository rep = (OfferRepository)context.getBean("offerRepository");
        rep.updateOffer(offer);
        String sqlQuery = String.format("WHERE offer_id = %s",offer.getOfferId());
        EmptySpecification spec = new EmptySpecification(sqlQuery);
        List<Offer> testOffer = rep.query(spec);
        assertEquals(offer.getOfferId(),testOffer.get(0).getOfferId());
        assertEquals(offer.getName(),testOffer.get(0).getName());
    }

}