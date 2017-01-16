package Repository;

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
        offers.add(offer);
    }

    @Override
    public void removeOffer(Offer offer) {

    }

    @Override
    public void updateOffer(Offer offer) {

    }

    @Override
    public List query(OfferSpecification specification) {
        List<Offer> specificOffers = new ArrayList<>();
        String sql = "SELECT * FROM offer WHERE " + specification.toSqlClauses();
        DBConnection.selectOffers(sql,specificOffers);
        return specificOffers;
    }
}
