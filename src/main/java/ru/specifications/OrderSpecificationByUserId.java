package ru.specifications;


import ru.interfaces.specification.Specification;

public class OrderSpecificationByUserId implements Specification {
    private int userId;

    public OrderSpecificationByUserId(int userId) {
        this.userId = userId;
    }


    public String toSqlClauses() {
        return String.format("WHERE user_id = %s",userId);
    }
}
