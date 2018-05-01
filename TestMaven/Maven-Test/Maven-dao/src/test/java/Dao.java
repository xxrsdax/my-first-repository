import bean.Customer;
import dao.CustomerDao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-dao.xml")
public class Dao {
    @Resource(name = "customerDao")
    private CustomerDao customerDao;
    @Test
    public void fun1(){
        Customer customer = customerDao.findById(1L);
        System.out.println(customer.getCustName());
    }
}
