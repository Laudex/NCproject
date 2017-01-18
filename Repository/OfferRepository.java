package Repository;

import DBclasses.DBChanger;
import DBclasses.DBConnection;
import Entity.Offer;
import Interfaces.Repository.IOfferRepository;
import Interfaces.Specification.OfferSpecification;

import java.util.ArrayList;
import java.util.List;


public class OfferRepository implements IOfferRepository {
    private List<Offer> offers = new ArrayList<>();
    @Override
    public void addOffer(Offer offer) {
        String sqlQuery = String.format("INSERT INTO offer (offer_id, name) VALUES (%s,\'%s\');", offer.getOfferId(),offer.getName());
        System.out.println(sqlQuery);
        DBChanger.changeEntity(sqlQuery);
    }

    @Override
    public void removeOffer(Offer offer) {
        String sqlQuery = String.format("DELETE FROM offer WHERE offer_id = %s;",offer.getOfferId());
        System.out.println(sqlQuery);
        DBChanger.changeEntity(sqlQuery);
    }

    @Override
    public void updateOffer(Offer offer) {
        String sqlQuery = String.format("UPDATE offer SET name = \'%s\' WHERE offer_id = %s;",offer.getName(),offer.getOfferId());
        System.out.println(sqlQuery);
        DBChanger.changeEntity(sqlQuery);
    }

    @Override
    public List query(OfferSpecification specification) {
        List<Offer> specificOffers = new ArrayList<>();
        String sql = "SELECT * FROM offer WHERE " + specification.toSqlClauses();
        DBConnection.selectOffers(sql,specificOffers);
        return specificOffers;
    }
}
