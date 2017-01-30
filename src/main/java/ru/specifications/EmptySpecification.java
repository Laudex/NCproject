package ru.specifications;


import ru.interfaces.specification.Specification;

public class EmptySpecification implements Specification {
    private String query;
    public EmptySpecification() {
        this.query = "";
    }

    public EmptySpecification(String query) {
        this.query = query;
    }

    public String toSqlClauses() {
        return query;
    }
}
