package com.briup.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.briup.bean.Book;
import com.briup.common.exception.DaoException;
import com.briup.common.util.GetConnection;
import com.briup.dao.IBookDao;

import java.sql.Statement;

public class IBookDaoImpl implements  IBookDao{
	@Override
	public Map<Long, Book> getAllBooks() throws Exception {
		// TODO Auto-generated method stub
		//从数据库获取全部的书本信息
		Connection conn=GetConnection.getConnection();
		
		String sql="select * from tbl_book";
		Statement st=conn.createStatement();
		ResultSet set =st.executeQuery(sql);
		Map<Long, Book> map=new HashMap<Long, Book>();
		while(set.next()){
			long id =set.getLong("book_id");
			String name = set.getString("name");
			double	price=set.getDouble("price");
			Book book =new Book(id,name,price);
			map.put(id, book);
		}
		return map;
	}
	@Override
	public Book getBookById(long id) throws DaoException {
		// TODO Auto-generated method stub
		//获得每本书的id
		Connection conn=GetConnection.getConnection();
		String sql="select * from tbl_book where Book_id="+id;
		try {
			Statement st=conn.createStatement();
			ResultSet set=st.executeQuery(sql);
			while(set.next()){
				Long book_id=set.getLong("Book_id");
				String name=set.getString("name");
				double price=set.getDouble("price");
				Book book=new Book(book_id, name, price);
			}
		} catch (SQLException e) {
			throw new   DaoException("数据库异常");
		}
		
		
		return null;
	}
	

}
