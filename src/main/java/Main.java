import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.entity.Orders;
import ru.entity.User;
import ru.repository.OrderRepository;
import ru.repository.UserRepository;
import ru.specifications.UserSpecificationByAdmin;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        //Orders orders = new Orders(11,2,2,"2011-11-11");
        UserSpecificationByAdmin spec = new UserSpecificationByAdmin(false);
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        UserRepository rep = (UserRepository) context.getBean("userRepository");
        List<User> specificUsers = rep.query(spec);
        for (int i = 0; i < specificUsers.size();i++) {
            System.out.println(specificUsers.get(i).getUserId());
            System.out.println(specificUsers.get(i).getName());
            System.out.println(specificUsers.get(i).getIsAdmin());
            System.out.println("-----------");
        }

        //30 days


    }
}
