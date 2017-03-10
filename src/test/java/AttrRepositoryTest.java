import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.entity.Attr;
import org.junit.Test;
import ru.repository.AttrRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/beans.xml"})
@Transactional
public class AttrRepositoryTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    AttrRepository rep;

    @Test
    public void addAttr() throws Exception {
        Attr attr = new Attr(6,"test");
        rep.addAttr(attr);
        String sqlQuery = String.format("SELECT * FROM attr WHERE attr_id = %s",attr.getAttrId());
        List<Attr> testAttr = jdbcTemplate.query(sqlQuery, new RowMapper<Attr>(){
            public Attr mapRow(ResultSet rs, int rowNum) throws SQLException {
                Attr nextAttr = new Attr();
                nextAttr.setAttrId(rs.getInt("attr_id"));
                nextAttr.setName(rs.getString("name"));
                return nextAttr;
            }
        });
        assertEquals(attr.getAttrId(),testAttr.get(0).getAttrId());
        assertEquals(attr.getName(),testAttr.get(0).getName());
    }

    @Test
    public void removeAttr() throws Exception {
        Attr attr = new Attr(5,"test");
        rep.removeAttr(attr);
        String sqlQuery = String.format("SELECT * FROM attr WHERE attr_id = %s",attr.getAttrId());
        List<Attr> testAttr = jdbcTemplate.query(sqlQuery, new RowMapper<Attr>(){
            public Attr mapRow(ResultSet rs, int rowNum) throws SQLException {
                Attr nextAttr = new Attr();
                nextAttr.setAttrId(rs.getInt("attr_id"));
                nextAttr.setName(rs.getString("name"));
                return nextAttr;
            }
        });
        assertEquals(testAttr.size(),0);
    }

    @Test
    public void updateAttr() throws Exception {
        Attr attr = new Attr(4,"test");
        rep.updateAttr(attr);
        String sqlQuery = String.format("SELECT * FROM attr WHERE attr_id = %s",attr.getAttrId());
        List<Attr> testAttr = jdbcTemplate.query(sqlQuery, new RowMapper<Attr>(){
            public Attr mapRow(ResultSet rs, int rowNum) throws SQLException {
                Attr nextAttr = new Attr();
                nextAttr.setAttrId(rs.getInt("attr_id"));
                nextAttr.setName(rs.getString("name"));
                return nextAttr;
            }
        });
        assertEquals(attr.getAttrId(),testAttr.get(0).getAttrId());
        assertEquals(attr.getName(),testAttr.get(0).getName());
    }

}