package ru.repository;

import ru.dbclasses.DBChanger;
import ru.dbclasses.DBConnection;
import ru.entity.Orders;
import ru.interfaces.repository.IOrderRepository;
import ru.interfaces.specification.Specification;

import java.util.ArrayList;
import java.util.List;


public class OrderRepository implements IOrderRepository {

    public void addOrders(Orders order) {
        String sqlQuery = String.format("INSERT INTO orders (user_id, offer_id, start_date) VALUES (%s, %s, \'%s\');",order.getUserId(),order.getOfferId(),order.getStartDate());
        System.out.println(sqlQuery);
        DBChanger.changeEntity(sqlQuery);
    }

    public void removeOrders(Orders orders) {
        String sqlQuery = String.format("DELETE FROM orders WHERE order_id = %s;",orders.getOrderId());
        System.out.println(sqlQuery);
        DBChanger.changeEntity(sqlQuery);
    }

    public void updateOrders(Orders orders) {
        String sqlQuery = String.format("UPDATE orders SET user_id = %s, offer_id = %s, start_date = \'%s\' WHERE order_id = %s;",orders.getUserId(),orders.getOfferId(),orders.getStartDate(),orders.getOrderId());
        System.out.println(sqlQuery);
        DBChanger.changeEntity(sqlQuery);
    }

    public List query(Specification specification) {
        List<Orders> specificOrders = new ArrayList<Orders>();
        String sql = "SELECT * FROM orders " + specification.toSqlClauses();
        DBConnection.selectOrders(sql,specificOrders);
        return specificOrders;
    }
}
