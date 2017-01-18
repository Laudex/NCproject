package Repository;

import DBclasses.DBChanger;
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
        String sqlQuery = String.format("INSERT INTO orders (order_id, user_id, offer_id, start_date) VALUES (%s, %s, %s, \'%s\');",order.getOrderId(),order.getUserId(),order.getOfferId(),order.getStartDate());
        System.out.println(sqlQuery);
        DBChanger.changeEntity(sqlQuery);
    }

    @Override
    public void removeOrders(Orders orders) {
        String sqlQuery = String.format("DELETE FROM orders WHERE order_id = %s;",orders.getOrderId());
        System.out.println(sqlQuery);
        DBChanger.changeEntity(sqlQuery);
    }

    @Override
    public void updateOrders(Orders orders) {
        String sqlQuery = String.format("UPDATE orders SET user_id = %s, offer_id = %s, start_date = \'%s\' WHERE order_id = %s;",orders.getUserId(),orders.getOfferId(),orders.getStartDate(),orders.getOrderId());
        System.out.println(sqlQuery);
        DBChanger.changeEntity(sqlQuery);
    }

    @Override
    public List query(OrderSpecification specification) {
        List<Orders> specificOrders = new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE " + specification.toSqlClauses();
        DBConnection.selectOrders(sql,specificOrders);
        return specificOrders;
    }
}
