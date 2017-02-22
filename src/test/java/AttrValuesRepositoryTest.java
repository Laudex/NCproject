import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.entity.AttrValues;
import org.junit.Test;
import ru.repository.AttrValuesRepository;
import ru.specifications.EmptySpecification;
import java.util.List;
import static org.junit.Assert.*;


public class AttrValuesRepositoryTest {
    ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
    @Test
    public void addAttrValues() throws Exception {
        AttrValues attrValues = new AttrValues(5,4,"test");
        AttrValuesRepository rep = (AttrValuesRepository)context.getBean("attrValRepository");
        rep.addAttrValues(attrValues);
        String sqlQuery = String.format("WHERE offer_id = %s AND attr_id = %s",attrValues.getOfferId(),attrValues.getAttrId());
        EmptySpecification spec = new EmptySpecification(sqlQuery);
        List<AttrValues> testAttrValues = rep.query(spec);
        assertEquals(attrValues.getOfferId(),testAttrValues.get(0).getOfferId());
        assertEquals(attrValues.getAttrId(),testAttrValues.get(0).getAttrId());
        assertEquals(attrValues.getValue(),testAttrValues.get(0).getValue());
    }

    @Test
    public void removeAttrValues() throws Exception {
        AttrValues attrValues = new AttrValues(5,4,"30 days");
        AttrValuesRepository rep = (AttrValuesRepository) context.getBean("attrValRepository");
        rep.removeAttrValues(attrValues);
        String sqlQuery = String.format("WHERE offer_id = %s AND attr_id = %s",attrValues.getOfferId(),attrValues.getAttrId());
        EmptySpecification spec = new EmptySpecification(sqlQuery);
        List<AttrValues> testAttrValues = rep.query(spec);
        assertEquals(testAttrValues.size(),0);
    }

    @Test
    public void updateAttrValues() throws Exception {
        AttrValues attrValues = new AttrValues(5,4,"30 days");
        AttrValuesRepository rep = (AttrValuesRepository)context.getBean("attrValRepository");
        rep.updateAttrValues(attrValues);
        String sqlQuery = String.format("WHERE offer_id = %s AND attr_id = %s",attrValues.getOfferId(),attrValues.getAttrId());
        EmptySpecification spec = new EmptySpecification(sqlQuery);
        List<AttrValues> testAttrValues = rep.query(spec);
        assertEquals(attrValues.getOfferId(),testAttrValues.get(0).getOfferId());
        assertEquals(attrValues.getAttrId(),testAttrValues.get(0).getAttrId());
        assertEquals(attrValues.getValue(),testAttrValues.get(0).getValue());
    }

}
