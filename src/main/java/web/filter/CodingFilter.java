package web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


/** Filter for UTF-8 coding
 * @author Jakub
 *
 */
public class CodingFilter implements Filter {


    public CodingFilter() {
    }

 
    public void init(FilterConfig filterConfig) throws ServletException {

    }

   
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    		request.setCharacterEncoding("UTF-8");
            chain.doFilter(request, response);
    }

   
    public void destroy() {

    }
}
