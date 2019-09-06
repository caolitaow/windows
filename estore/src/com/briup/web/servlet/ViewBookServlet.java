package com.briup.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.briup.common.exception.ServiceException;
import com.briup.service.IBookService;
import com.briup.service.impl.IBookServiceImpl;

public class ViewBookServlet extends HttpServlet{

	IBookService service =new IBookServiceImpl();
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			String id=req.getParameter("id");
			Long book_id=Long.parseLong(id);
			try {
				service.findBookById(book_id);
				req.getRequestDispatcher("viewBook.jsp").forward(req, resp);
			} catch (ServiceException e) {
				req.setAttribute("msg", e.getMessage());
				req.getRequestDispatcher("index.jsp").forward(req, resp);
			}
			
		
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			this.doPost(req, resp);
	}
}
