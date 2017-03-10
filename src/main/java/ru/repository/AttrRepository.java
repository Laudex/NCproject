package ru.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;
import ru.entity.Attr;
import ru.interfaces.repository.IAttrRepository;
import ru.interfaces.specification.Specification;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class AttrRepository implements IAttrRepository {


    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }



    public void addAttr(Attr attr) {
        String sqlQuery = String.format("INSERT INTO attr (attr_id, name) VALUES (%s,\'%s\');", attr.getAttrId(),attr.getName());
        jdbcTemplate.update(sqlQuery);
    }


    public void removeAttr(Attr attr) {
        String sqlQuery = String.format("DELETE FROM attr WHERE attr_id = %s;",attr.getAttrId());
        jdbcTemplate.update(sqlQuery);
    }


    public void updateAttr(Attr attr) {
        String sqlQuery = String.format("UPDATE attr SET name = \'%s\' WHERE attr_id = %s;",attr.getName(),attr.getAttrId());
        jdbcTemplate.update(sqlQuery);
    }


    public List query(Specification specification) {
        String sql = "SELECT * FROM attr " + specification.toSqlClauses();
        List<Attr> specificAttrs = jdbcTemplate.query(sql, new RowMapper<Attr>(){
            public Attr mapRow(ResultSet rs, int rowNum) throws SQLException {
                Attr nextAttr = new Attr();
                nextAttr.setAttrId(rs.getInt("attr_id"));
                nextAttr.setName(rs.getString("name"));
                return nextAttr;
            }
        });
        return specificAttrs;
    }
}
