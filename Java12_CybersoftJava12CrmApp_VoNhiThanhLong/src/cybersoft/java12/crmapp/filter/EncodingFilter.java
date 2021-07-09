package cybersoft.java12.crmapp.filter;

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

import cybersoft.java12.crmapp.util.UrlConst;
@WebFilter(urlPatterns = {UrlConst.ROOT})
public class EncodingFilter implements Filter{
	private int requestCount =1;
	

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		int count = requestCount++;
		// xử lý request
		System.out.println("Request count >>> "+count);
		req.setCharacterEncoding("UTF-8");
		System.out.println("Set charset for Request: "+count);
		chain.doFilter(request, response);
		//xử lý response
		resp.setCharacterEncoding("UTF-8");
		System.out.println("Set charset for Reponse"+count);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}



}
