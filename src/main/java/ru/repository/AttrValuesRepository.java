package ru.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.entity.AttrValues;
import ru.interfaces.repository.IAttrValuesRepository;
import ru.interfaces.specification.Specification;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class AttrValuesRepository implements IAttrValuesRepository {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    public void addAttrValues(AttrValues attrValues) {
        String sqlQuery = String.format("INSERT INTO attr_values (offer_id, attr_id, value) VALUES (%s,%s,\'%s\');", attrValues.getOfferId(),attrValues.getAttrId(),attrValues.getValue());
        jdbcTemplate.update(sqlQuery);
    }


    public void removeAttrValues(AttrValues attrValues) {
        String sqlQuery = String.format("DELETE FROM attr_values WHERE offer_id = %s;",attrValues.getOfferId());
        jdbcTemplate.update(sqlQuery);
    }

    public void updateAttrValues(AttrValues attrValues) {
        String sqlQuery = String.format("UPDATE attr_values SET value = \'%s\' WHERE offer_id = %s AND attr_id = %s;",attrValues.getValue(),attrValues.getOfferId(),attrValues.getAttrId());
        jdbcTemplate.update(sqlQuery);
    }

    public List query(Specification specification) {
        String sql = "SELECT * FROM attr_values " + specification.toSqlClauses();
        List<AttrValues> specificAttrsValues = this.jdbcTemplate.query(sql, new RowMapper<AttrValues>(){
            public AttrValues mapRow(ResultSet rs, int rowNum) throws SQLException {
                AttrValues nextAttrValues = new AttrValues();
                nextAttrValues.setOfferId(rs.getInt("offer_id"));
                nextAttrValues.setAttrId(rs.getInt("attr_id"));
                nextAttrValues.setValue(rs.getString("value"));
                return nextAttrValues;
            }
        });
        return specificAttrsValues;
    }
}
