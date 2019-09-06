package com.briup.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.briup.bean.Customer;

public class LoginAfterFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
			
			HttpServletRequest req =(HttpServletRequest) arg0;
			HttpSession session = req.getSession();
			Customer cus =(Customer) session.getAttribute("cus");
			if(cus ==null){
				req.setAttribute("msg", "请先登录");
				req.getRequestDispatcher("../index.jsp").forward(req, resp);
			}else{
				chain.doFilter(req, resp);
			}
			
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
}
