package com.itheima.crm.service;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;
import com.itheima.crm.domain.Customer;
import sun.plugin.javascript.JSClassLoader;

@Transactional
public class CustomerServiceImpl implements ICustomerService {
	private JdbcTemplate jdbcTemplate;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	public List<Customer> findAll() {
		String sql = "select * from t_customer";
		List<Customer> list = jdbcTemplate.query(sql, new RowMapper<Customer>(){
			public Customer mapRow(ResultSet rs, int arg1) throws SQLException {
				int id = rs.getInt("id");//根据字段名称从结果集中获取对应的值
				String name = rs.getString("name");
				String station = rs.getString("station");
				String telephone = rs.getString("telephone");
				String address = rs.getString("address");
				String decidedzone_id = rs.getString("decidedzone_id");
				return new Customer(id, name, station, telephone, address, decidedzone_id);
			}
		});
		return list;
	}

	/**
	 * 查询未与定区关联的客户
	 * */
	public List<Customer> findListNoAssociationDecidedzone() {
		String sql = "select * from t_customer where decidedzone_id is null"; //sql语句

		List<Customer>  customerList= jdbcTemplate.query(sql, new RowMapper<Customer>() {

				public Customer mapRow(ResultSet rs, int arg1) throws SQLException {
					int id = rs.getInt("id");//根据字段名称从结果集中获取对应的值
					String name = rs.getString("name");
					String station = rs.getString("station");
					String telephone = rs.getString("telephone");
					String address = rs.getString("address");
					String decidedzone_id = rs.getString("decidedzone_id");
					return new Customer(id, name, station, telephone, address, decidedzone_id);
				}

		});
		return customerList;
	}

	/**
	 * 查询已关联指定的客户
	 * */
	public List<Customer> findListAssociationDecidedzone(String decidedzone_id) {
		String sql = "select * from t_customer where decidedzone_id = ? "; //sql语句

		List<Customer>  customerList= jdbcTemplate.query(sql, new RowMapper<Customer>() {

			public Customer mapRow(ResultSet rs, int arg1) throws SQLException {
				int id = rs.getInt("id");//根据字段名称从结果集中获取对应的值
				String name = rs.getString("name");
				String station = rs.getString("station");
				String telephone = rs.getString("telephone");
				String address = rs.getString("address");
				String decidedzone_id = rs.getString("decidedzone_id");
				return new Customer(id, name, station, telephone, address, decidedzone_id);
			}
		},decidedzone_id);
		return customerList;
	}

	 /**
	  * 将与指定定区关联的用户的定区清空
	  * */
	public void emptyByDecidedzone(String decidedzoneId) {
		//书写sql语句
		String sql = "update t_customer set decidedzone_id = null where decidedzone_id = ? ";
		jdbcTemplate.update(sql,decidedzoneId);
	}

	  /**
	  * 让指定id的用户，与指定定区关联
	  * */
	public void updateByCustomerIds(String[] customerIds,String decidedzoneId) {
		//遍历数组
		for(String id : customerIds) {
			updateByCustomerId(id,decidedzoneId);
		}
	}

	/**
	 * 让指定id的用户，与指定定区关联
	 * */
	public void updateByCustomerId(String customerIds,String decidedzoneId){
		//书写sql语句
		String sql = "update t_customer set  decidedzone_id = ? where id = ? ";
		jdbcTemplate.update(sql,decidedzoneId,customerIds);
	}

	/**
	 * 根据电话查询用户信息
	 * */
	public Customer findCustomerByTelephone(String telephone) {
		//书写sql 语句
		String sql = "select * from t_customer where telephone = ?";
//		Customer customer = jdbcTemplate.queryForObject(sql, Customer.class, telephone);
		List<Customer> list = jdbcTemplate.query(sql, new RowMapper<Customer>() {

			public Customer mapRow(ResultSet resultSet, int i) throws SQLException {
				int id = resultSet.getInt("id");//根据字段名称从结果集中获取对应的值
				String name = resultSet.getString("name");
				String station = resultSet.getString("station");
				String telephone = resultSet.getString("telephone");
				String address = resultSet.getString("address");
				String decidedzone_id = resultSet.getString("decidedzone_id");
				return new Customer(id, name, station, telephone, address, decidedzone_id);
			}
		}, telephone);

		if(list != null && list.size()>0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 根据用户地址查询定区id        使用该方法，
	 * */
	public String findDecidedzoneIdByAddress(String address) {
		//书写sql语句
		String sql = "select decidedzone_id from t_customer where address = ?";
		String decidedzoneId = jdbcTemplate.queryForObject(sql, String.class, address);
		return decidedzoneId;
	}

}
