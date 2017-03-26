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
        jdbcTemplate.update("INSERT INTO attr_values (offer_id, attr_id, value) VALUES (?,?,?);", attrValues.getOfferId(),attrValues.getAttrId(),attrValues.getValue());
    }


    public void removeAttrValues(AttrValues attrValues) {
        jdbcTemplate.update("DELETE FROM attr_values WHERE offer_id = ?",attrValues.getOfferId());
    }

    public void updateAttrValues(AttrValues attrValues) {
        jdbcTemplate.update("UPDATE attr_values SET value = ? WHERE offer_id = ? AND attr_id = ?;",attrValues.getValue(),attrValues.getOfferId(),attrValues.getAttrId());
    }

    public List query(Specification specification) {
        String sql = "SELECT * FROM attr_values " + specification.toSqlClauses();
        List<AttrValues> specificAttrsValues = jdbcTemplate.query(sql, new RowMapper<AttrValues>(){
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
