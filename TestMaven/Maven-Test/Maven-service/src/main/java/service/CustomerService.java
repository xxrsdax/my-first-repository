package service;

import bean.Customer;

public interface CustomerService {
    Customer findById(Long id);
}
