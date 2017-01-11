package Specifications;

import Interfaces.Specification.OrderSpecification;


public class OrderSpecificationByUserId implements OrderSpecification {
    private String userId;

    public OrderSpecificationByUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toSqlClauses() {
        return String.format("user_id = %s",userId);
    }
}
