package ru.interfaces.repository;

import ru.entity.Orders;
import ru.interfaces.specification.Specification;

import java.util.List;


public interface IOrderRepository {
    void addOrders(Orders order);
    void removeOrders(Orders orders);
    void updateOrders(Orders orders);

    List query(Specification specification);
}
