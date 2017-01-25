package Specifications;


import Interfaces.Specification.Specification;

public class OrderSpecificationByStartDate implements Specification {
    private String startDate;

    public OrderSpecificationByStartDate(String startDate) {
        this.startDate = startDate;
    }


    public String toSqlClauses() {
        return String.format("WHERE start_date = \'%s\'",startDate);
    }
}
