package com.sixppl.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import com.sixppl.main.Application;

@WebFilter(filterName="IP Filter", urlPatterns={ "/*" })
public class IPFilter implements Filter {
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//TODO: Implement Unique IP tracking analytics
//		Application.getSharedInstance().addRemoteAddress(request.getRemoteAddr());
		chain.doFilter(request, response);
	}
	
	public void destroy() {
	}
	
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
