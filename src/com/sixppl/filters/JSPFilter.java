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
import javax.servlet.http.HttpServletResponse;

/**
 * Filter and block all direct JSP page requests
 * @author atton16
 *
 */
@WebFilter(filterName="JSP Filter", urlPatterns={ "*.jsp" })
public class JSPFilter implements Filter{
	@Override
	public void  doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req;
		HttpServletResponse res;
		String contextPath;
		String fullURI;
		String URI;
		
		req = (HttpServletRequest) request;
		res = (HttpServletResponse) response;
		contextPath = req.getContextPath();
		fullURI = req.getRequestURI();
		URI = fullURI.substring(contextPath.length());

		if(URI.endsWith(".jsp"))
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
}
