package dao;

import bean.Customer;

public interface CustomerDao  {
    public Customer findById(Long id);

}
