package Repository;

import DBclasses.DBChanger;
import DBclasses.DBConnection;
import Entity.Offer;
import Interfaces.Repository.IOfferRepository;
import Interfaces.Specification.OfferSpecification;

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
    public List query(OfferSpecification specification) {
        List<Offer> specificOffers = new ArrayList<Offer>();
        String sql = "SELECT * FROM offer WHERE " + specification.toSqlClauses();
        DBConnection.selectOffers(sql,specificOffers);
        return specificOffers;
    }
}
