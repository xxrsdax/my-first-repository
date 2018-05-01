package dao.impl;

import bean.Customer;
import dao.CustomerDao;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

public class CustomerDaoImpl extends HibernateDaoSupport implements CustomerDao {
    @Override
    public Customer findById(Long id) {
        return getHibernateTemplate().get(Customer.class, id);
    }
}
