package utils;

import beans.UserBean;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

@WebFilter(filterName = "AdminFilter", urlPatterns = {"/admin/*"})
public class AdminFilter implements Filter {
    
    @Inject
    UserBean userBean;    
    
    public AdminFilter() {
    }    
    
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        if (userBean.getIsAdmin() == false) {
            ( (HttpServletResponse) response).sendRedirect("../index.jsf");
        }
        try {
            chain.doFilter(request, response);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }       
}
