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
        String sqlQuery = String.format("INSERT INTO offer (name) VALUES (\'%s\');", offer.getName());
        jdbcTemplate.update(sqlQuery);
    }

    public void removeOffer(Offer offer) {
        String sqlQuery = String.format("DELETE FROM offer WHERE offer_id = %s;", offer.getOfferId());
        jdbcTemplate.update(sqlQuery);
    }

    public void updateOffer(Offer offer) {
        String sqlQuery = String.format("UPDATE offer SET name = \'%s\' WHERE offer_id = %s;", offer.getName(), offer.getOfferId());
        jdbcTemplate.update(sqlQuery);
    }

    public List query(Specification specification) {
        String sql = "SELECT * FROM offer " + specification.toSqlClauses();
        List<Offer> specificOffers = this.jdbcTemplate.query(sql, new RowMapper<Offer>() {
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
