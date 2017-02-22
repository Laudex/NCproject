import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.entity.Attr;
import org.junit.Test;
import ru.repository.AttrRepository;
import ru.specifications.EmptySpecification;

import java.util.List;

import static org.junit.Assert.*;


public class AttrRepositoryTest {
    ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
    @Test
    public void addAttr() throws Exception {
        Attr attr = new Attr(5,"test");
        AttrRepository rep = (AttrRepository)context.getBean("attrRepository");
        rep.addAttr(attr);
        String sqlQuery = String.format("WHERE attr_id = %s",attr.getAttrId());
        EmptySpecification spec = new EmptySpecification(sqlQuery);
        List<Attr> testAttr = rep.query(spec);
        assertEquals(attr.getAttrId(),testAttr.get(0).getAttrId());
        assertEquals(attr.getName(),testAttr.get(0).getName());
    }

    @Test
    public void removeAttr() throws Exception {
        Attr attr = new Attr(5,"test");
        AttrRepository rep = (AttrRepository)context.getBean("attrRepository");
        rep.removeAttr(attr);
        String sqlQuery = String.format("WHERE attr_id = %s",attr.getAttrId());
        EmptySpecification spec = new EmptySpecification(sqlQuery);
        List<Attr> testAttr = rep.query(spec);
        assertEquals(testAttr.size(),0);
    }

    @Test
    public void updateAttr() throws Exception {
        Attr attr = new Attr(5,"period");
        AttrRepository rep =(AttrRepository)context.getBean("attrRepository");
        rep.updateAttr(attr);
        String sqlQuery = String.format("WHERE attr_id = %s",attr.getAttrId());
        EmptySpecification spec = new EmptySpecification(sqlQuery);
        List<Attr> testAttr = rep.query(spec);
        assertEquals(attr.getAttrId(),testAttr.get(0).getAttrId());
        assertEquals(attr.getName(),testAttr.get(0).getName());
    }

}