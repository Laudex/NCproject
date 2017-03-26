package ru.repository;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.entity.Offer;
import ru.interfaces.repository.IOfferRepository;
import ru.interfaces.specification.Specification;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class OfferRepository implements IOfferRepository {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addOffer(Offer offer) {
        if (offer.getOfferId() != 0){
            jdbcTemplate.update("INSERT INTO offer (offer_id, name) VALUES (?,?);",offer.getOfferId(), offer.getName());
        } else {
            jdbcTemplate.update("INSERT INTO offer (name) VALUES (?);", offer.getName());
        }
    }

    public void removeOffer(Offer offer) {
        jdbcTemplate.update("DELETE FROM offer WHERE offer_id = ?;", offer.getOfferId());
    }

    public void updateOffer(Offer offer) {
        jdbcTemplate.update("UPDATE offer SET name = ? WHERE offer_id = ?;", offer.getName(), offer.getOfferId());
    }

    public List query(Specification specification) {
        String sql = "SELECT * FROM offer " + specification.toSqlClauses();
        List<Offer> specificOffers = jdbcTemplate.query(sql, new RowMapper<Offer>() {
            public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
                Offer nextOffer = new Offer();
                nextOffer.setOffer_id(rs.getInt("offer_id"));
                nextOffer.setName(rs.getString("name"));
                return nextOffer;
            }
        });
        return specificOffers;
    }
}
