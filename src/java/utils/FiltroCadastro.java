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

@WebFilter(filterName = "FiltroCadastro", urlPatterns = {"/cadastro.jsf"})

public class FiltroCadastro implements Filter{

   @Inject
   UserBean usrBean;
   
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            if (!usrBean.getIsAdmin()) {
                ((HttpServletResponse) response).sendRedirect("login.jsf");
            }else{
                chain.doFilter(request, response);
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
