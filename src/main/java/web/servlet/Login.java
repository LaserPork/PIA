package web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.auth.AuthenticationService;


/**
 * Servlet for signing in
 * first page that user sees
 * allows for signing or redirecting to registration or homepage
 *
 */
public class Login extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String USERNAME_PARAMETER = "inputName";
    private static final String PASSWORD_PARAMETER = "inputPassword";
 
    private static final String ERR_ATTRIBUTE = "err";

    private AuthenticationService authService;

    public Login(AuthenticationService authService) {
        this.authService = authService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter(USERNAME_PARAMETER);
        String password = req.getParameter(PASSWORD_PARAMETER);
        boolean authenticated = authService.authenticate(req.getSession(), username, password);
        if(authenticated) {
            resp.sendRedirect("home");
        } else {
            req.setAttribute(ERR_ATTRIBUTE, "Invalid credentials!");
            req.getRequestDispatcher("/loginPage.jsp").forward(req, resp);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	if(authService.isLoggedIn(req.getSession())) {
    		resp.sendRedirect("home");
    	}else {
    		req.getRequestDispatcher("/loginPage.jsp").forward(req, resp);    		
    	}
    }
}
