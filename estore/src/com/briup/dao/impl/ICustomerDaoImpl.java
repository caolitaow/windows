package com.briup.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.briup.bean.Customer;
import com.briup.common.exception.CustomerDaoException;
import com.briup.common.util.GetConnection;
import com.briup.dao.ICustomerDao;



public class ICustomerDaoImpl implements ICustomerDao{
	
	@Override
	public void register(Customer cus) throws CustomerDaoException {
		try {
			//注册驱动并获取连接
			Connection  conn  = GetConnection.getConnection();
			String sql  ="insert into  tbl_customer(address,email,name,password,telephone,zip) values(?,?,?,?,?,?)";
			//导入java.sql.statement,导错包也会出错
			PreparedStatement  ps  = conn.prepareStatement(sql);
			ps.setString(1, cus.getAddress());
			ps.setString(2, cus.getEmail());
			ps.setString(3, cus.getName());
			ps.setString(4, cus.getPassword());
			ps.setString(5, cus.getTelephone());
			ps.setString(6, cus.getZip());
			//数据执行收sql语句
			ps.execute();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			 e.getStackTrace();
			 throw  new CustomerDaoException("数据库异常");
		}
	}
	

	@Override
	public void deleteUser(long id) throws CustomerDaoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Customer cus) throws CustomerDaoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Customer findUserByName(String name) throws CustomerDaoException {
		Customer cus = null;
		try {
			Connection conn=GetConnection.getConnection();
			String sql="select * from tbl_customer where name='"+name+"'";
			Statement st=conn.createStatement();
			ResultSet set=st.executeQuery(sql);
			while(set.next()){
				Long id=set.getLong("customer_id");
				String address =set.getString("address");
				String email =set.getString("email");
				String name1 = set.getString("name");
				String password = set.getString("password");
				String  telephone =set.getString("telephone");
				String zip =set.getString("zip");
				cus =new Customer(id, name1, password, zip, address, telephone, email);
			}
			
		} catch (SQLException e) {
			 e.getStackTrace();
			 throw  new CustomerDaoException("数据库异常");
		}
		return cus;
	}

}
