package com.briup.service.impl;

import com.briup.bean.Customer;
import com.briup.common.exception.CustomerDaoException;
import com.briup.common.exception.CustomerServiceException;
import com.briup.dao.ICustomerDao;
import com.briup.dao.impl.ICustomerDaoImpl;
import com.briup.service.ICustomerService;

public class ICustomerServiceImpl implements ICustomerService{
	ICustomerDao dao=new ICustomerDaoImpl();
	@Override
	public void register(Customer customer) throws CustomerServiceException {
		// TODO Auto-generated method stub
		
		try {
			Customer cus=dao.findUserByName(customer.getName());
			if(cus==null){
				dao.register(customer);
			}else{
				throw new CustomerServiceException("该用户已注册");
			}
			
		} catch (CustomerDaoException e) {
			throw new CustomerServiceException(e.getMessage());
		}
	}

	@Override
	public Customer login(String name, String password)
			throws CustomerServiceException {
		ICustomerDao dao=new ICustomerDaoImpl();
		// TODO Auto-generated method stub
		Customer cus=null;
		try {
			cus=dao.findUserByName(name);
			if(cus!=null){
					if(!cus.getPassword().equals(password)){
						throw new CustomerServiceException("密码输入有误");
					}	
			}
			else{
				throw new CustomerServiceException("用户名输入有误");
			}
		} catch (CustomerDaoException e) {
			e.printStackTrace();
			throw new CustomerServiceException(e.getMessage());
		}
		 return cus;
	}

	@Override
	public void update(Customer customer) throws CustomerServiceException {
		// TODO Auto-generated method stub
		
	}
	
}
