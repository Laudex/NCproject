import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.entity.Offer;
import ru.entity.Orders;
import org.junit.Test;
import ru.repository.OfferRepository;
import ru.repository.OrderRepository;


import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/beans.xml"})
@Transactional
public class OrderRepositoryTest {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    OrderRepository rep;
    @Autowired
    OfferRepository rep2;


    @Test
    public void addOrders() throws Exception {
        Offer offer = new Offer(100,"testOffer");
        Orders order = new Orders(11,1,100,"2016-12-12");
        rep2.addOffer(offer);
        rep.addOrders(order);
        String sqlQuery = String.format("SELECT * FROM orders WHERE order_id = %s",order.getOrderId());
        List<Orders> testOrder = jdbcTemplate.query(sqlQuery, new RowMapper<Orders>(){
            public Orders mapRow(ResultSet rs, int rowNum) throws SQLException {
                Orders nextOrder = new Orders();
                nextOrder.setOrderId(rs.getInt("order_id"));
                nextOrder.setOfferId(rs.getInt("offer_id"));
                nextOrder.setUserId(rs.getInt("user_id"));
                nextOrder.setStartDate(rs.getString("start_date"));
                return nextOrder;
            }
        });
        assertEquals(order.getOrderId(),testOrder.get(0).getOrderId());
        assertEquals(order.getUserId(),testOrder.get(0).getUserId());
        assertEquals(order.getOfferId(),testOrder.get(0).getOfferId());
        assertEquals(order.getStartDate(),testOrder.get(0).getStartDate());
    }

    @Test
    public void removeOrders() throws Exception {
        Offer offer = new Offer(100,"testOffer");
        Orders order = new Orders(11,1,100,"2016-12-12");
        rep2.addOffer(offer);
        rep.addOrders(order);
        rep.removeOrders(order);
        String sqlQuery = String.format("SELECT * FROM orders WHERE order_id = %s",order.getOrderId());
        List<Orders> testOrder = jdbcTemplate.query(sqlQuery, new RowMapper<Orders>(){
            public Orders mapRow(ResultSet rs, int rowNum) throws SQLException {
                Orders nextOrder = new Orders();
                nextOrder.setOrderId(rs.getInt("order_id"));
                nextOrder.setOfferId(rs.getInt("offer_id"));
                nextOrder.setUserId(rs.getInt("user_id"));
                nextOrder.setStartDate(rs.getString("start_date"));
                return nextOrder;
            }
        });
        assertEquals(testOrder.size(),0);
    }

    @Test
    public void updateOrders() throws Exception {
        Offer offer = new Offer(100,"testOffer");
        Orders order = new Orders(11,2,100,"2016-12-12");
        Orders updateOrder = new Orders(11,2,100,"2010-01-01");
        rep2.addOffer(offer);
        rep.addOrders(order);
        rep.updateOrders(updateOrder);
        String sqlQuery = String.format("SELECT * FROM orders WHERE order_id = %s",order.getOrderId());
        List<Orders> testOrder = jdbcTemplate.query(sqlQuery, new RowMapper<Orders>(){
            public Orders mapRow(ResultSet rs, int rowNum) throws SQLException {
                Orders nextOrder = new Orders();
                nextOrder.setOrderId(rs.getInt("order_id"));
                nextOrder.setOfferId(rs.getInt("offer_id"));
                nextOrder.setUserId(rs.getInt("user_id"));
                nextOrder.setStartDate(rs.getString("start_date"));
                return nextOrder;
            }
        });
        assertEquals(updateOrder.getOrderId(),testOrder.get(0).getOrderId());
        assertEquals(updateOrder.getUserId(),testOrder.get(0).getUserId());
        assertEquals(updateOrder.getOfferId(),testOrder.get(0).getOfferId());
        assertEquals(updateOrder.getStartDate(),testOrder.get(0).getStartDate());
    }

}
