package ru.specifications;


import ru.interfaces.specification.Specification;

public class OfferSpecificationByName implements Specification {

    private String name;

    public OfferSpecificationByName(String name) {
        super();
        this.name = name;
    }


    public String toSqlClauses() {
        return String.format("WHERE name like \'%s\'", name);
    }
}
