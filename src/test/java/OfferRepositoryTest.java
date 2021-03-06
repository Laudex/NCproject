import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.entity.Offer;
import org.junit.Test;
import ru.repository.OfferRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/beans.xml"})
@Transactional
public class OfferRepositoryTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    OfferRepository rep;
    @Test
    public void addOffer() throws Exception {
        Offer offer = new Offer(200,"Offer 6");
        rep.addOffer(offer);
        String sqlQuery = String.format("SELECT * FROM offer WHERE offer_id = %s",offer.getOfferId());
        List<Offer> testOffer = jdbcTemplate.query(sqlQuery, new RowMapper<Offer>() {
            public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
                Offer nextOffer = new Offer();
                nextOffer.setOffer_id(rs.getInt("offer_id"));
                nextOffer.setName(rs.getString("name"));
                return nextOffer;
            }
        });
        assertEquals(offer.getOfferId(),testOffer.get(0).getOfferId());
        assertEquals(offer.getName(),testOffer.get(0).getName());
    }

    @Test
    public void removeOffer() throws Exception {
        Offer offer = new Offer(101,"Test offer");
        rep.addOffer(offer);
        rep.removeOffer(offer);
        String sqlQuery = String.format("SELECT * FROM offer WHERE offer_id = %s",offer.getOfferId());
        List<Offer> testOffer = jdbcTemplate.query(sqlQuery, new RowMapper<Offer>() {
            public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
                Offer nextOffer = new Offer();
                nextOffer.setOffer_id(rs.getInt("offer_id"));
                nextOffer.setName(rs.getString("name"));
                return nextOffer;
            }
        });
        assertEquals(testOffer.size(),0);
    }

    @Test
    public void updateOffer() throws Exception {
        Offer offerTest = new Offer(100, "Test offer");
        Offer offer = new Offer(100,"test");
        rep.addOffer(offerTest);
        rep.updateOffer(offer);
        String sqlQuery = String.format("SELECT * FROM offer WHERE offer_id = %s",offer.getOfferId());
        List<Offer> testOffer = jdbcTemplate.query(sqlQuery, new RowMapper<Offer>() {
            public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
                Offer nextOffer = new Offer();
                nextOffer.setOffer_id(rs.getInt("offer_id"));
                nextOffer.setName(rs.getString("name"));
                return nextOffer;
            }
        });
        assertEquals(offer.getOfferId(),testOffer.get(0).getOfferId());
        assertEquals(offer.getName(),testOffer.get(0).getName());
    }

}