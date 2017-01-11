package Specifications;

import Interfaces.Specification.AttrValuesSpecification;


public class AttrValuesSpecificationByOfferId implements AttrValuesSpecification {
    private int offerId;

    public AttrValuesSpecificationByOfferId(int offerId) {
        this.offerId = offerId;
    }

    @Override
    public String toSqlClauses() {
        return String.format("offer_id = %s",offerId);
    }
}
