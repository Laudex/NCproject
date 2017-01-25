package Specifications;

import Interfaces.Specification.Specification;


public class UserSpecificationByName implements Specification {
    private String name;

    public UserSpecificationByName(String name){
        this.name = name;
    }
    public String toSqlClauses() {
        return String.format("WHERE name = \'%s\'",name);
    }
}
