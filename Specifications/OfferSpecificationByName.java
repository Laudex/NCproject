package Specifications;

import Interfaces.Specification.OfferSpecification;


public class OfferSpecificationByName implements OfferSpecification {

    private String name;

    public OfferSpecificationByName(String name) {
        super();
        this.name = name;
    }

    @Override
    public String toSqlClauses() {
        return String.format("name like \'%s\'", name);
    }
}
