package Specifications;

import Interfaces.Specification.AttrValuesSpecification;


public class AttrValuesSpecificationByAttrId implements AttrValuesSpecification {
    private int attrId;

    public AttrValuesSpecificationByAttrId(int attrId) {
        this.attrId = attrId;
    }


    public String toSqlClauses() {
        return String.format("attr_id = %s",attrId);
    }
}
