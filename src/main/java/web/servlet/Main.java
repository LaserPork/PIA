package web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import domain.ValidationException;
import manager.PostManager;

import web.auth.AuthenticationService;
import web.service.FormService;

/**
 * Servlet that listens on /home
 * shows all posts to unsigned users
 * shows posts of user and his friends to signed users
 */
public class Main extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    private static final String ERR_ATTRIBUTE = "err";

    private AuthenticationService authService;
    private FormService formService;
    
    private PostManager postManager;

    public Main(PostManager postManager, AuthenticationService authService, FormService formService) {
        this.postManager = postManager;
        this.authService = authService;
        this.formService = formService;
    }
    
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try{
			formService.setPostsHomePage(req, authService, postManager);
			req.getRequestDispatcher("/WEB-INF/homePage.jsp").forward(req, resp);
		}catch (Exception e) {
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
        	formService.serveLike(req,authService, postManager);
        	formService.servePost(req, authService, postManager);
            formService.setPostsHomePage(req, authService, postManager);
            req.getRequestDispatcher("/WEB-INF/homePage.jsp").forward(req, resp);
        } catch (ValidationException e) {
            errorDispatch(e.getMessage(), req, resp);
        }
    }
	
    private void errorDispatch(String err, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(ERR_ATTRIBUTE, err);
        req.getRequestDispatcher("/WEB-INF/homePage.jsp").forward(req, resp);
    }
}
