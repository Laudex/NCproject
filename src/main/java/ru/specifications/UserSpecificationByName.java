package ru.specifications;

import ru.interfaces.specification.Specification;


public class UserSpecificationByName implements Specification {
    private String name;

    public UserSpecificationByName(String name){
        this.name = name;
    }
    public String toSqlClauses() {
        return String.format("WHERE name = \'%s\'",name);
    }
}
