/*import ru.dbclasses.DBConnectionFactory;
import ru.entity.Orders;
import org.junit.Test;
import ru.repository.OrderRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.Assert.*;


public class OrderRepositoryTest {
    @Test
    public void addOrders() throws Exception {
        Orders order = new Orders(100,1,2,"2013-12-12");
        OrderRepository rep = new OrderRepository();
        rep.addOrders(order);
        Orders testOrder = new Orders();
        String sqlQuery = String.format("SELECT * FROM orders WHERE order_id = %s",order.getOrderId());
        Connection connection;
        Statement stmt;
        try {
            connection = DBConnectionFactory.conFactory();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);
            if(rs.next()){
                testOrder.setOrderId(rs.getInt("order_id"));
                testOrder.setUserId(rs.getInt("user_id"));
                testOrder.setOfferId(rs.getInt("offer_id"));
                testOrder.setStartDate(rs.getString("start_date"));
            }
            rs.close();
            stmt.close();
            connection.close();
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
        assertEquals(order.getOrderId(),testOrder.getOrderId());
        assertEquals(order.getUserId(),testOrder.getUserId());
        assertEquals(order.getOfferId(),testOrder.getOfferId());
        assertEquals(order.getStartDate(),testOrder.getStartDate());
    }

    @Test
    public void removeOrders() throws Exception {
        Orders order = new Orders(11,1,2,"2013-12-12");
        OrderRepository rep = new OrderRepository();
        rep.removeOrders(order);
        Orders testOrder = null;
        String sqlQuery = String.format("SELECT * FROM orders WHERE order_id = %s",order.getOrderId());
        Connection connection;
        Statement stmt;
        try {
            connection = DBConnectionFactory.conFactory();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);
            if(rs.next()){
                testOrder = new Orders();
                testOrder.setOrderId(rs.getInt("order_id"));
                testOrder.setUserId(rs.getInt("user_id"));
                testOrder.setOfferId(rs.getInt("offer_id"));
                testOrder.setStartDate(rs.getString("start_date"));
            }
            rs.close();
            stmt.close();
            connection.close();
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
        assertNull(testOrder);
    }

    @Test
    public void updateOrders() throws Exception {
        Orders order = new Orders(11,2,3,"2020-12-12");
        OrderRepository rep = new OrderRepository();
        rep.updateOrders(order);
        Orders testOrder = new Orders();
        String sqlQuery = String.format("SELECT * FROM orders WHERE order_id = %s",order.getOrderId());
        Connection connection;
        Statement stmt;
        try {
            connection = DBConnectionFactory.conFactory();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);
            if(rs.next()){
                testOrder.setOrderId(rs.getInt("order_id"));
                testOrder.setUserId(rs.getInt("user_id"));
                testOrder.setOfferId(rs.getInt("offer_id"));
                testOrder.setStartDate(rs.getString("start_date"));
            }
            rs.close();
            stmt.close();
            connection.close();
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
        assertEquals(order.getOrderId(),testOrder.getOrderId());
        assertEquals(order.getUserId(),testOrder.getUserId());
        assertEquals(order.getOfferId(),testOrder.getOfferId());
        assertEquals(order.getStartDate(),testOrder.getStartDate());
    }

}
*/