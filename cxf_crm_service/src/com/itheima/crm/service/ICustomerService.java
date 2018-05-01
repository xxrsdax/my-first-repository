package com.itheima.crm.service;

import java.util.List;

import javax.jws.WebService;

import com.itheima.crm.domain.Customer;
@WebService
public interface ICustomerService {
	/**
	 * 查询全部的用户
	 * */
	public List<Customer> findAll();
	/**
	 * 查询未关联定区的用户
	 * */
	public List<Customer> findListNoAssociationDecidedzone();
	/**
	 * 查询已关联指定定区的用户
	 * */
	public List<Customer> findListAssociationDecidedzone(String decidedzone_id);
	/**
	 *   将与指定定区关联的用户的定区清空
	 * */
	public void emptyByDecidedzone(String decidedzoneId);
	/**
	 * 让指定id的用户，与指定区域关联
	 * */
	public void  updateByCustomerIds(String[] customerIds,String decidedzoneId);
	/**
	 * 让指定id的用户，与指定区域关联
	 * */
	public void  updateByCustomerId(String customerId,String decidedzoneId);

	/**
	 * l 根据客户的手机号查询客户信息*/
	public Customer findCustomerByTelephone(String telephone);

	/**l 根据客户的取件地址查询定区id
	*/
	public String findDecidedzoneIdByAddress(String address);

}
