package action;

import bean.Customer;
import com.opensymphony.xwork2.ActionSupport;
import service.CustomerService;

import java.util.ArrayList;
import java.util.List;

public class CustomerAction extends ActionSupport {


    private Customer customer;

    private CustomerService customerService;


    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    public String add() throws Exception {

        customer = customerService.findById(customer.getCustId());

        ArrayList list = new ArrayList();
        list.add("sss");
        return SUCCESS;
    }
}
