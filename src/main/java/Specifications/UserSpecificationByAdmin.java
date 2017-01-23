package Specifications;

import Interfaces.Specification.UserSpecification;


public class UserSpecificationByAdmin implements UserSpecification {
    private boolean isAdmin;

    public UserSpecificationByAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }


    public String toSqlClauses() {
        return String.format("is_admin = %b",isAdmin);
    }
}
