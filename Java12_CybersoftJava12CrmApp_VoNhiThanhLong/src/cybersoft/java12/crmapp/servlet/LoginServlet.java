package cybersoft.java12.crmapp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cybersoft.java12.crmapp.repository.UserRepository;
import cybersoft.java12.crmapp.util.JspConst;
import cybersoft.java12.crmapp.util.ServletConst;
import cybersoft.java12.crmapp.util.UrlConst;
@WebServlet(name = ServletConst.LOGIN, urlPatterns = {
		UrlConst.LOGIN,
		UrlConst.LOGOUT,
		UrlConst.FORGOT_PASSWORD,
		UrlConst.SIGNUP
})
public class LoginServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()) {
		case UrlConst.LOGIN:
			//cookies demo
			Cookie cookie = new Cookie("firstcookie", "Thisisthefirstcookie");
			cookie.setMaxAge(60);
			resp.addCookie(cookie);
			// kiem tra cookie
			Cookie[] cookies = req.getCookies();
			for (int i =0;i<cookies.length;i++) {
				if (cookies[i].getName().equals("email")) {
					String email = cookies[i].getValue();
					req.setAttribute("email", email);
				}
			}
			HttpSession currentSession = req.getSession();
			currentSession.setAttribute("pingo","This is the first session attribute.");
			currentSession.setMaxInactiveInterval(60*60);
			String status = String.valueOf(req.getSession().getAttribute("status"));
			if (!status.equals("null")) {
				resp.sendRedirect(req.getContextPath()+UrlConst.HOME);
			}  else
			req.getRequestDispatcher(JspConst.LOGIN).forward(req, resp);
			break;
		case UrlConst.LOGOUT:
			req.getSession().invalidate();
			resp.sendRedirect(req.getContextPath()+UrlConst.LOGIN);
			break;
		default:
			break;
		}
	
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		switch (req.getServletPath()) {
		case UrlConst.LOGIN:
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			boolean isLogin = true;
			String remember = req.getParameter("rememberUsername");
			if (remember!=null) {
				Cookie cookie = new Cookie("email",email);
				cookie.setMaxAge(60*60);
				resp.addCookie(cookie);
			}
			// session demo
			HttpSession currentSession = req.getSession();
			String pingo =(String) currentSession.getAttribute("pingo");
			System.out.println("Pingo: "+ pingo);
			
			System.out.println("Email: "+email+", Rememberme: "+remember);
			
			
			// logic dang nhap
			UserRepository ur = new UserRepository();
			if (email==null||password==null) {
				isLogin = false;
				
			}
			
			else if (!ur.login(email, password)){
				isLogin = false;
			}
			if (isLogin) {
				currentSession.setAttribute("status", "Logged in successfully");
				resp.sendRedirect(req.getContextPath()+UrlConst.HOME);
			}
			else 
			resp.sendRedirect(req.getContextPath()+UrlConst.LOGIN);
			break;
		case UrlConst.LOGOUT:
			
			break;
		default:
			break;
		}
	}
}