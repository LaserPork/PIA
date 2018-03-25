package web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import web.auth.AuthenticationService;


public class AuthenticationGuard implements Filter {

    private AuthenticationService authService;

    public AuthenticationGuard(AuthenticationService authService) {
        this.authService = authService;
    }

 
    public void init(FilterConfig filterConfig) throws ServletException {

    }

   
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        boolean allowed = authService.isLoggedIn(req.getSession());
        if(allowed) {
            chain.doFilter(request, response);
        } else {
            req.getRequestDispatcher("/").forward(request, response);
        }
    }

   
    public void destroy() {

    }
}
