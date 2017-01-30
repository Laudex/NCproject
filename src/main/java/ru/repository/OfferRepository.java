package ru.repository;

import ru.dbclasses.DBChanger;
import ru.dbclasses.DBConnection;
import ru.entity.Offer;
import ru.interfaces.repository.IOfferRepository;
import ru.interfaces.specification.Specification;

import java.util.ArrayList;
import java.util.List;


public class OfferRepository implements IOfferRepository {

    public void addOffer(Offer offer) {
        String sqlQuery = String.format("INSERT INTO offer (offer_id, name) VALUES (%s,\'%s\');", offer.getOfferId(),offer.getName());
        DBChanger.changeEntity(sqlQuery);
    }

    public void removeOffer(Offer offer) {
        String sqlQuery = String.format("DELETE FROM offer WHERE offer_id = %s;",offer.getOfferId());
        DBChanger.changeEntity(sqlQuery);
    }
    public void updateOffer(Offer offer) {
        String sqlQuery = String.format("UPDATE offer SET name = \'%s\' WHERE offer_id = %s;",offer.getName(),offer.getOfferId());
        DBChanger.changeEntity(sqlQuery);
    }
    public List query(Specification specification) {
        List<Offer> specificOffers = new ArrayList<Offer>();
        String sql = "SELECT * FROM offer " + specification.toSqlClauses();
        DBConnection.selectOffers(sql,specificOffers);
        return specificOffers;
    }
}
