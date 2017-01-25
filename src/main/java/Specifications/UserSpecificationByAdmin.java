package Specifications;


import Interfaces.Specification.Specification;

public class UserSpecificationByAdmin implements Specification {
    private boolean isAdmin;

    public UserSpecificationByAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }


    public String toSqlClauses() {
        return String.format("WHERE is_admin = %b",isAdmin);
    }
}
