package ru.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.entity.Orders;
import ru.interfaces.repository.IOrderRepository;
import ru.interfaces.specification.Specification;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class OrderRepository implements IOrderRepository {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addOrders(Orders order) {
        if (order.getOrderId() != 0){
            String sqlQuery = String.format("INSERT INTO orders (order_id, user_id, offer_id, start_date) VALUES (%s,%s, %s, \'%s\');", order.getOrderId(),order.getUserId(), order.getOfferId(), order.getStartDate());
            jdbcTemplate.update(sqlQuery);
        } else {
            String sqlQuery = String.format("INSERT INTO orders (user_id, offer_id, start_date) VALUES (%s, %s, \'%s\');", order.getUserId(), order.getOfferId(), order.getStartDate());
            jdbcTemplate.update(sqlQuery);
        }
    }

    public void removeOrders(Orders orders) {
        String sqlQuery = String.format("DELETE FROM orders WHERE order_id = %s;",orders.getOrderId());
        jdbcTemplate.update(sqlQuery);
    }

    public void updateOrders(Orders orders) {
        String sqlQuery = String.format("UPDATE orders SET user_id = %s, offer_id = %s, start_date = \'%s\' WHERE order_id = %s;",orders.getUserId(),orders.getOfferId(),orders.getStartDate(),orders.getOrderId());
        jdbcTemplate.update(sqlQuery);
    }

    public List query(Specification specification) {
        String sql = "SELECT * FROM orders " + specification.toSqlClauses();
        List<Orders> specificOrders = this.jdbcTemplate.query(sql, new RowMapper<Orders>(){
            public Orders mapRow(ResultSet rs, int rowNum) throws SQLException {
                Orders nextOrder = new Orders();
                nextOrder.setOrderId(rs.getInt("order_id"));
                nextOrder.setOfferId(rs.getInt("offer_id"));
                nextOrder.setUserId(rs.getInt("user_id"));
                nextOrder.setStartDate(rs.getString("start_date"));
                return nextOrder;
            }
        });
        return specificOrders;
    }
}
