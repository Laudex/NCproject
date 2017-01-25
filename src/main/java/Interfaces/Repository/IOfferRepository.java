package Interfaces.Repository;

import Entity.Offer;
import Interfaces.Specification.Specification;

import java.util.List;


public interface IOfferRepository {
    void addOffer(Offer offer);
    void removeOffer(Offer offer);
    void updateOffer(Offer offer);

    List query(Specification specification);
}
