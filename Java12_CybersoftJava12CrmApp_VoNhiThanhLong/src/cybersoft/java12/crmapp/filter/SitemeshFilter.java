package cybersoft.java12.crmapp.filter;

import javax.servlet.annotation.WebFilter;

import com.opensymphony.sitemesh.webapp.SiteMeshFilter;

@WebFilter(filterName = "sitemesh", urlPatterns = "/*")
public class SitemeshFilter extends SiteMeshFilter {
	
}
