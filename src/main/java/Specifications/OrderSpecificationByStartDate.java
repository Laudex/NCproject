package Specifications;

import Interfaces.Specification.OrderSpecification;


public class OrderSpecificationByStartDate implements OrderSpecification{
    private String startDate;

    public OrderSpecificationByStartDate(String startDate) {
        this.startDate = startDate;
    }


    public String toSqlClauses() {
        return String.format("start_date = \'%s\'",startDate);
    }
}
