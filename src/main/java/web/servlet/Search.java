package web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.ValidationException;

import manager.UserManager;
import web.auth.AuthenticationService;
import web.service.FormService;

/**
 * Servlet displaying search returns
 * listens on /search
 * only for signed
 */
public class Search extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String ERR_ATTRIBUTE = "err";

    private UserManager userManager;
    
    private AuthenticationService authenticationService;
    private FormService formService;

    public Search(UserManager userManager, AuthenticationService authenticationService, FormService formService) {
        this.userManager = userManager;
        this.authenticationService = authenticationService;
        this.formService = formService;
    }
    
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		errorDispatch("Use search bar to search for users", req, resp);
    }
	
	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			if(formService.serveLogOut(req)) {
				resp.sendRedirect("");
				return;
			}
			formService.serveFriendRequest(req, authenticationService, userManager);
			formService.serveSearch(req, authenticationService, userManager);
			req.getRequestDispatcher("/WEB-INF/searchPage.jsp").forward(req, resp);
		} catch (ValidationException e) {
			errorDispatch(e.getMessage(), req, resp);
		}
		
    }
	
	  private void errorDispatch(String err, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	        req.setAttribute(ERR_ATTRIBUTE, err);
	        req.getRequestDispatcher("/WEB-INF/searchPage.jsp").forward(req, resp);
	    }
   
}
