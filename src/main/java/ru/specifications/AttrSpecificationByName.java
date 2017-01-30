package ru.specifications;


import ru.interfaces.specification.Specification;

public class AttrSpecificationByName implements Specification {
    private String name;

    public AttrSpecificationByName(String name) {
        this.name = name;
    }


    public String toSqlClauses() {
        return String.format("WHERE name like \'%s\'",name);
    }
}
