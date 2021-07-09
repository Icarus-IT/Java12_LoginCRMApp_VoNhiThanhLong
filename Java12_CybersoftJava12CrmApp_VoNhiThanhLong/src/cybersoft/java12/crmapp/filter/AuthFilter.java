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

@WebFilter(urlPatterns = UrlConst.ROOT)
public class AuthFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		String servletPath = req.getServletPath();
		
		if (servletPath.startsWith(UrlConst.ASSETS)||servletPath.startsWith(UrlConst.LOGIN)) {
			
			chain.doFilter(request, response);
			
		} else {
			String status = String.valueOf(req.getSession().getAttribute("status"));
			System.out.println("STATUS"+status);
			if (status.equals("null")) {
				resp.sendRedirect(req.getContextPath()+UrlConst.LOGIN);
			} else {
				chain.doFilter(request, response);
			}
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}
}