package com.briup.service.impl;

import java.util.Map;

import com.briup.bean.Book;
import com.briup.common.exception.DaoException;
import com.briup.common.exception.ServiceException;
import com.briup.dao.IBookDao;
import com.briup.dao.impl.IBookDaoImpl;
import com.briup.service.IBookService;

public class IBookServiceImpl implements IBookService{
	IBookDao dao =new IBookDaoImpl();
	@Override
	public Map<Long, Book> listAllBook() throws Exception {
		// TODO Auto-generated method stub
		Map<Long, Book> map=dao.getAllBooks();
		return map;
	}

	@Override
	public Book findBookById(long id) throws ServiceException {
		// TODO Auto-generated method stub
		Book book=null;
		try {
			book = dao.getBookById(id);
		} catch (DaoException e) {
			throw new ServiceException("该书已经下架，请浏览其他商品，谢谢");
		}
		return book;
	}
	
}
