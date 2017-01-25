package Specifications;


import Interfaces.Specification.Specification;

public class OrderSpecificationByOfferId implements Specification {
    private int offerId;

    public OrderSpecificationByOfferId(int offerId) {
        this.offerId = offerId;
    }


    public String toSqlClauses() {
        return String.format("WHERE offer_id = %s",offerId);
    }
}
