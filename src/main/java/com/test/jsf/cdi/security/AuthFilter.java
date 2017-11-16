package com.test.jsf.cdi.security;

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

import org.apache.shiro.SecurityUtils;

import com.test.jsf.cdi.model.Functionality;

@WebFilter("/pages/*")
public class AuthFilter implements Filter {

    @Override
    public void destroy() {}

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest request = (HttpServletRequest) req;
        
        String path = request.getRequestURI();
        path = path.replace(request.getContextPath(), "");  
        
        if(!"/pages/home.xhtml".equals(path) && 
                !SecurityUtils.getSubject().isPermitted(buildPermission(path))) {
            
            HttpServletResponse response = (HttpServletResponse) res;
            response.sendRedirect(request.getContextPath() + "/pages/home.xhtml");
            
        } else {
        
            chain.doFilter(req, res);
        }
        
    }
    
    private FunctionalityPermission buildPermission(String path) {
        Functionality functionality = new Functionality();
        functionality.setPath(path);
        
        return new FunctionalityPermission(functionality);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {}

}
