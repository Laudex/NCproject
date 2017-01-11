package Specifications;

import Interfaces.Specification.OrderSpecification;


public class OrderSpecificationByOfferId implements OrderSpecification {
    private int offerId;

    public OrderSpecificationByOfferId(int offerId) {
        this.offerId = offerId;
    }

    @Override
    public String toSqlClauses() {
        return String.format("offer_id = %s",offerId);
    }
}
