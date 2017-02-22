import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.entity.Orders;
import org.junit.Test;
import ru.repository.OrderRepository;
import ru.specifications.EmptySpecification;
import java.util.List;
import static org.junit.Assert.*;


public class OrderRepositoryTest {
    ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
    @Test
    public void addOrders() throws Exception {
        Orders order = new Orders(100,1,2,"2013-12-12");
        OrderRepository rep = (OrderRepository)context.getBean("orderRepository");
        rep.addOrders(order);
        String sqlQuery = String.format("WHERE order_id = %s",order.getOrderId());
        EmptySpecification spec = new EmptySpecification(sqlQuery);
        List<Orders> testOrder = rep.query(spec);
        assertEquals(order.getOrderId(),testOrder.get(0).getOrderId());
        assertEquals(order.getUserId(),testOrder.get(0).getUserId());
        assertEquals(order.getOfferId(),testOrder.get(0).getOfferId());
        assertEquals(order.getStartDate(),testOrder.get(0).getStartDate());
    }

    @Test
    public void removeOrders() throws Exception {
        Orders order = new Orders(100,1,2,"2013-12-12");
        OrderRepository rep = (OrderRepository)context.getBean("orderRepository");
        rep.removeOrders(order);
        String sqlQuery = String.format("WHERE order_id = %s",order.getOrderId());
        EmptySpecification spec = new EmptySpecification(sqlQuery);
        List<Orders> testOrder = rep.query(spec);
        assertEquals(testOrder.size(),0);
    }

    @Test
    public void updateOrders() throws Exception {
        Orders order = new Orders(100,2,1,"2020-12-12");
        OrderRepository rep = (OrderRepository)context.getBean("orderRepository");
        rep.updateOrders(order);
        String sqlQuery = String.format("WHERE order_id = %s",order.getOrderId());
        EmptySpecification spec = new EmptySpecification(sqlQuery);
        List<Orders> testOrder = rep.query(spec);
        assertEquals(order.getOrderId(),testOrder.get(0).getOrderId());
        assertEquals(order.getUserId(),testOrder.get(0).getUserId());
        assertEquals(order.getOfferId(),testOrder.get(0).getOfferId());
        assertEquals(order.getStartDate(),testOrder.get(0).getStartDate());
    }

}
