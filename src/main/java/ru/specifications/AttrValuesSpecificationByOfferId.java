package ru.specifications;


import ru.interfaces.specification.Specification;

public class AttrValuesSpecificationByOfferId implements Specification {
    private int offerId;

    public AttrValuesSpecificationByOfferId(int offerId) {
        this.offerId = offerId;
    }


    public String toSqlClauses() {
        return String.format("WHERE offer_id = %s",offerId);
    }
}
