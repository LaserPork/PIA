package web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.User;
import manager.UserManager;
import web.auth.AuthenticationService;
import web.service.FormService;

/**
 * Servlet that listens on /friends
 * shows all users friends
 */
public class Friends extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private static final String ERR_ATTRIBUTE = "err";

    private AuthenticationService authService;
    private UserManager userManager;
    private FormService formService;
    
	public Friends(UserManager userManager, AuthenticationService authenticationService, FormService formService) {
		this.formService = formService;
		this.authService = authenticationService;
		this.userManager = userManager;
	}

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(authService.isLoggedIn(req.getSession())) {
			setFriends(req);
			req.getRequestDispatcher("/WEB-INF/friendsPage.jsp").forward(req, resp);
		}else {
			errorDispatch("You must be logged in to see your friends", req, resp);
		}
        
    }
	
	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(authService.isLoggedIn(req.getSession())) {
			if(formService.serveLogOut(req)) {
				resp.sendRedirect("");
				return;
			}
			setFriends(req);
			req.getRequestDispatcher("/WEB-INF/friendsPage.jsp").forward(req, resp);
		}else {
			errorDispatch("You must be logged in to see your friends", req, resp);
		}
    }
	
	private void setFriends(HttpServletRequest req) {
		List<User> friends = userManager.fetchFriends(authService.getLoggedUser(req.getSession()));
		req.setAttribute("friends", friends);
	}
	
	 private void errorDispatch(String err, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	        req.setAttribute(ERR_ATTRIBUTE, err);
	        req.getRequestDispatcher("/WEB-INF/homePage.jsp").forward(req, resp);
	    }
	
}
