package ru.interfaces.repository;

import ru.entity.Offer;
import ru.interfaces.specification.Specification;

import java.util.List;


public interface IOfferRepository {
    void addOffer(Offer offer);
    void removeOffer(Offer offer);
    void updateOffer(Offer offer);

    List query(Specification specification);
}
