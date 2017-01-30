package ru.specifications;


import ru.interfaces.specification.Specification;

public class AttrValuesSpecificationByAttrId implements Specification {
    private int attrId;

    public AttrValuesSpecificationByAttrId(int attrId) {
        this.attrId = attrId;
    }


    public String toSqlClauses() {
        return String.format("WHERE attr_id = %s",attrId);
    }
}
