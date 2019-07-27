package com.briup.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.briup.bean.Customer;
import com.briup.common.exception.CustomerServiceException;
import com.briup.service.IBookService;
import com.briup.service.ICustomerService;
import com.briup.service.impl.ICustomerServiceImpl;


public class RegisterServlet extends HttpServlet{
		ICustomerService service =new ICustomerServiceImpl();
		protected void doGet(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
			// TODO Auto-generated method stub
				String name=req.getParameter("name");
				String password=req.getParameter("password");
				String zip=req.getParameter("zip");
				String telephone=req.getParameter("telephone");
				String address=req.getParameter("address");
				String email=req.getParameter("email");
				Customer cus=
					new Customer(name, password, zip, address, telephone, email);
				
				try {
					service.register(cus);
					resp.sendRedirect("login.jsp");
				} catch (CustomerServiceException e) {
					req.setAttribute("error",e.getMessage());
					req.getRequestDispatcher("register.jsp").forward(req, resp);
				}
				
		}
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
			this.doGet(req, resp);
		}
}
