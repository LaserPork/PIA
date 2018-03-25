package web.servlet;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.User;
import domain.ValidationException;

import manager.UserManager;
import web.auth.AuthenticationService;
import web.service.FormService;

/**
 * Servlet displaying all users pending friend requests
 * listens at /requests
 * only for signed users
 */
public class Requests extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private static final String ERR_ATTRIBUTE = "err";

    private AuthenticationService authService;
    
    private UserManager userManager;
    private FormService formService;

    public Requests(UserManager requesttManager, AuthenticationService authService, FormService formService) {
        this.userManager = requesttManager;
        this.authService = authService;
        this.formService = formService;
    }
    
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			setRequests(req,resp); 
			req.getRequestDispatcher("/WEB-INF/requestsPage.jsp").forward(req, resp);
        } catch (ValidationException e) {
            errorDispatch(e.getMessage(), req, resp);
        }
    }
	
	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
        	if(formService.serveLogOut(req)) {
				resp.sendRedirect("");
				return;
			}
        	formService.serveFriendRequest(req,authService, userManager);
            setRequests(req,resp);
            req.getRequestDispatcher("/WEB-INF/requestsPage.jsp").forward(req, resp);
        } catch (ValidationException e) {
            errorDispatch(e.getMessage(), req, resp);
        }
    }
	
	private void setRequests(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ValidationException {
		if(authService.isLoggedIn(req.getSession())) {
			List<User> requests = userManager.fetchRequestsTo(authService.getLoggedUser(req.getSession()));
			req.setAttribute("requests", requests);
		}else {
			throw new ValidationException("You must be logged in to view your friend requests");
		}
	}

    private void errorDispatch(String err, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(ERR_ATTRIBUTE, err);
        req.getRequestDispatcher("profile").forward(req, resp);
    }
}
