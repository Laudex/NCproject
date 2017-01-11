package Specifications;

import Interfaces.Specification.UserSpecification;


public class UserSpecificationByAdmin implements UserSpecification {

    public UserSpecificationByAdmin() {
        super();
    }

    @Override
    public String toSqlClauses() {
        return String.format("is_admin = true");
    }
}
