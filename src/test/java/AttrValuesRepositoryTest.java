import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.entity.Attr;
import ru.entity.AttrValues;
import org.junit.Test;
import ru.entity.Offer;
import ru.repository.AttrRepository;
import ru.repository.AttrValuesRepository;
import ru.repository.OfferRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/beans.xml"})
@Transactional
public class AttrValuesRepositoryTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    AttrValuesRepository rep;
    @Autowired
    AttrRepository rep2;
    @Autowired
    OfferRepository rep3;

    @Test
    public void addAttrValues() throws Exception {
        Attr attr = new Attr(5,"test");
        AttrValues attrValues = new AttrValues(5,5,"test");
        rep2.addAttr(attr);
        rep.addAttrValues(attrValues);
        String sqlQuery = String.format("SELECT * FROM attr_values WHERE offer_id = %s AND attr_id = %s",attrValues.getOfferId(),attrValues.getAttrId());
        List<AttrValues> testAttrValues = jdbcTemplate.query(sqlQuery, new RowMapper<AttrValues>(){
            public AttrValues mapRow(ResultSet rs, int rowNum) throws SQLException {
                AttrValues nextAttrValues = new AttrValues();
                nextAttrValues.setOfferId(rs.getInt("offer_id"));
                nextAttrValues.setAttrId(rs.getInt("attr_id"));
                nextAttrValues.setValue(rs.getString("value"));
                return nextAttrValues;
            }
        });
        assertEquals(attrValues.getOfferId(),testAttrValues.get(0).getOfferId());
        assertEquals(attrValues.getAttrId(),testAttrValues.get(0).getAttrId());
        assertEquals(attrValues.getValue(),testAttrValues.get(0).getValue());
    }

    @Test
    public void removeAttrValues() throws Exception {
        Attr attr = new Attr(5,"test");
        Offer offer = new Offer(100,"test offer");
        AttrValues attrValues = new AttrValues(100,5,"test");
        rep2.addAttr(attr);
        rep3.addOffer(offer);
        rep.addAttrValues(attrValues);
        rep.removeAttrValues(attrValues);
        String sqlQuery = String.format("SELECT * FROM attr_values WHERE offer_id = %s AND attr_id = %s",attrValues.getOfferId(),attrValues.getAttrId());
        List<AttrValues> testAttrValues = jdbcTemplate.query(sqlQuery, new RowMapper<AttrValues>(){
            public AttrValues mapRow(ResultSet rs, int rowNum) throws SQLException {
                AttrValues nextAttrValues = new AttrValues();
                nextAttrValues.setOfferId(rs.getInt("offer_id"));
                nextAttrValues.setAttrId(rs.getInt("attr_id"));
                nextAttrValues.setValue(rs.getString("value"));
                return nextAttrValues;
            }
        });
        assertEquals(testAttrValues.size(),0);
    }

    @Test
    public void updateAttrValues() throws Exception {
        Attr attr = new Attr(5,"test");
        Offer offer = new Offer(100,"test offer");
        AttrValues attrValues = new AttrValues(100,5,"testUpdate");
        AttrValues updateAttrValues = new AttrValues(100,5,"testUpdate");
        rep2.addAttr(attr);
        rep3.addOffer(offer);
        rep.addAttrValues(attrValues);
        rep.updateAttrValues(updateAttrValues);
        String sqlQuery = String.format("SELECT * FROM attr_values WHERE offer_id = %s AND attr_id = %s",attrValues.getOfferId(),attrValues.getAttrId());
        List<AttrValues> testAttrValues = jdbcTemplate.query(sqlQuery, new RowMapper<AttrValues>(){
            public AttrValues mapRow(ResultSet rs, int rowNum) throws SQLException {
                AttrValues nextAttrValues = new AttrValues();
                nextAttrValues.setOfferId(rs.getInt("offer_id"));
                nextAttrValues.setAttrId(rs.getInt("attr_id"));
                nextAttrValues.setValue(rs.getString("value"));
                return nextAttrValues;
            }
        });
        assertEquals(attrValues.getOfferId(),testAttrValues.get(0).getOfferId());
        assertEquals(attrValues.getAttrId(),testAttrValues.get(0).getAttrId());
        assertEquals(attrValues.getValue(),testAttrValues.get(0).getValue());
    }

}
