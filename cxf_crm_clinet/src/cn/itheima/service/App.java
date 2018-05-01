package cn.itheima.service;

import java.util.List;

public class App {
    public static void main(String[] args){
        CustomerServiceImplService customerServiceImplService = new CustomerServiceImplService();
        ICustomerService poxy = customerServiceImplService.getCustomerServiceImplPort();
        List<Customer> all = poxy.findAll();
        for (Customer customer :all){
            System.out.println(customer.getName());
        }
    }

}
