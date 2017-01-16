package Interfaces.Repository;

import Entity.Orders;
import Interfaces.Specification.OrderSpecification;

import java.util.List;


public interface IOrderRepository {
    void addOrders(Orders order);
    void removeOrders(Orders orders);
    void updateOrders(Orders orders);

    List query(OrderSpecification specification);
}
