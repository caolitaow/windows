package com.briup.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.briup.bean.Book;
import com.briup.bean.OrderLine;
import com.briup.bean.ShoppingCart;
import com.briup.common.exception.ServiceException;
import com.briup.service.IBookService;
import com.briup.service.impl.IBookServiceImpl;

public class AddToCartServlet extends HttpServlet{
	
		private static final long serialVersionUID = 1L;
		IBookService service = new IBookServiceImpl();
		
	
		protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			
			int num=Integer.parseInt(req.getParameter("num"));
			Long id  = Long.parseLong(req.getParameter("id"));
			
			try {
				Book book = service.findBookById(id);
				HttpSession session = req.getSession();
				
				ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
				OrderLine order = new OrderLine();
				order.setNum(num);
				order.setBook(book);
				cart.addLine(order);
				
				for(OrderLine line :cart.getOrderlines()){
					System.out.println(line.getBook()+"----"+line.getNum());
				}
				
				session.setAttribute("cart", cart);
				req.setAttribute("msg", "加入购物车成功");
				req.getRequestDispatcher("../index.jsp").forward(req, resp);
			} catch (ServiceException e) {
				req.setAttribute("msg", "该本书已经下架，请重新选购");
				req.getRequestDispatcher("../index.jsp").forward(req, resp);
			}
		}
		protected void doPost(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
				this.doPost(req, resp);
				
		}
}
