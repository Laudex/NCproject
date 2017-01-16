package Repository;

import DBclasses.DBConnection;
import Entity.Orders;
import Interfaces.Repository.IOrderRepository;
import Interfaces.Specification.OrderSpecification;

import java.util.ArrayList;
import java.util.List;


public class OrderRepository implements IOrderRepository {
    private List<Orders> orders = new ArrayList<>();
    @Override
    public void addOrders(Orders order) {
        orders.add(order);
    }

    @Override
    public void removeOrders(Orders orders) {

    }

    @Override
    public void updateOrders(Orders orders) {

    }

    @Override
    public List query(OrderSpecification specification) {
        List<Orders> specificOrders = new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE " + specification.toSqlClauses();
        DBConnection.selectOrders(sql,specificOrders);
        return specificOrders;
    }
}
