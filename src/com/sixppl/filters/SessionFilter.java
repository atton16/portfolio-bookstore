package com.sixppl.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.sixppl.main.Application;

@WebFilter(filterName="Session Filter", urlPatterns={ "/*" })
public class SessionFilter implements Filter {
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//TODO: Implement Session Activity Tracker
		//		for analytics purpose
//		HttpServletRequest req = (HttpServletRequest) request;
//		if(!Application.getSharedInstance().getSessions().contains(req.getSession().getId())){
//			req.getSession().invalidate();
//		}
		chain.doFilter(request, response);
	}
	
	public void destroy() {
	}
	
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
