package Specifications;

import Interfaces.Specification.AttrSpecification;


public class AttrSpecificationByName implements AttrSpecification {
    private String name;

    public AttrSpecificationByName(String name) {
        this.name = name;
    }

    @Override
    public String toSqlClauses() {
        return String.format("name like \'%s\'",name);
    }
}
