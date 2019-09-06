package com.briup.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.briup.bean.Customer;
import com.briup.bean.ShoppingCart;
import com.briup.common.exception.CustomerServiceException;
import com.briup.service.ICustomerService;
import com.briup.service.impl.ICustomerServiceImpl;

public class LoginServlet extends HttpServlet{
	
	ICustomerService service =new ICustomerServiceImpl();
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
			String name=req.getParameter("name");
			String password=req.getParameter("password");
			
			try {
				Customer cus=service.login(name, password);
				//如果这个用户允许登录，那么我们需要将这个用户信保存到Session中
				//获取session
				HttpSession session = req.getSession();
				if(session.getAttribute("cus")==null){
					session.setAttribute("cus", cus);
				}
				
				ShoppingCart cart =(ShoppingCart) session.getAttribute("cart");
				
				if(cart == null){
					cart = new ShoppingCart();
					session.setAttribute("cart", cart);
				}
				resp.sendRedirect("index.jsp");
				req.setAttribute("name",name);
			} catch (CustomerServiceException e) {
				req.setAttribute("error",e.getMessage());
				req.getRequestDispatcher("login.jsp").forward(req, resp);
			}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			this.doGet(req, resp);
	}
}
