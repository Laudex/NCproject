package ru.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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
        jdbcTemplate.update("INSERT INTO attr (attr_id, name) VALUES (?,?);", attr.getAttrId(),attr.getName());
    }


    public void removeAttr(Attr attr) {
        jdbcTemplate.update("DELETE FROM attr WHERE attr_id = ?;",attr.getAttrId());
    }


    public void updateAttr(Attr attr) {
        jdbcTemplate.update("UPDATE attr SET name = ? WHERE attr_id = ?;",attr.getName(),attr.getAttrId());
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
