package service.impl;


import bean.Customer;
import dao.CustomerDao;
import service.CustomerService;

public class CustomerServiceImpl implements CustomerService {
    private CustomerDao customerDao;
    @Override
    public Customer findById(Long id) {
        Customer customer = customerDao.findById(id);
        return customer;
    }

    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }
}
