package Specifications;


import Interfaces.Specification.Specification;

public class AttrValuesSpecificationByValue implements Specification {
    private String attrValue;

    public AttrValuesSpecificationByValue(String attrValue) {
        this.attrValue = attrValue;
    }


    public String toSqlClauses() {
        return String.format("WHERE value like \'%s\'",attrValue);
    }
}
