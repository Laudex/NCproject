package Interfaces.Repository;

import Entity.Offer;
import Interfaces.Specification.OfferSpecification;

import java.util.List;


public interface IOfferRepository {
    void addOffer(Offer offer);
    void removeOffer(Offer offer);
    void updateOffer(Offer offer);

    List query(OfferSpecification specification);
}
