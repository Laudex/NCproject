package Specifications;

import Interfaces.Specification.AttrValuesSpecification;


public class AttrValuesSpecificationByValue implements AttrValuesSpecification {
    private String attrValue;

    public AttrValuesSpecificationByValue(String attrValue) {
        this.attrValue = attrValue;
    }


    public String toSqlClauses() {
        return String.format("value like \'%s\'",attrValue);
    }
}
