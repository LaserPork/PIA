package web.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;

import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import domain.User;

import manager.PostManager;
import manager.UserManager;
import web.auth.AuthenticationService;
import web.service.FormService;

/**
 * Servlet that listens on /profile
 * shows users profile or profiles of ther users
 * for other users their id must be defined after the path
 * example: /profile/1, /profile/2
 */
public class Profile extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
    private static final String ERR_ATTRIBUTE = "err";

    private PostManager postManager;
    private UserManager userManager;

    private AuthenticationService authService;
    private FormService formService;
    
	public Profile(PostManager postManager, UserManager userManager, AuthenticationService authenticationService, FormService formService) {
		 this.postManager = postManager;
	     this.authService = authenticationService;
	     this.userManager = userManager;
	     this.formService = formService;
	}

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = null;
		try{
			user = formService.getUserOfProfile(req, authService,userManager);
			req.setAttribute("profile", user);
			setAge(req, user);
			formService.setPostsProfilePage(req, user, postManager);
			req.getRequestDispatcher("/WEB-INF/profilePage.jsp").forward(req, resp);
		}catch (Exception e) {
			errorDispatch(e.getMessage(), req, resp);
		}
    }
	
	
	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = null;
		try{
			if(formService.serveLogOut(req)) {
				resp.sendRedirect("");
				return;
			}
			user = formService.getUserOfProfile(req, authService,userManager);
			req.setAttribute("profile", user);
			setAge(req, user);
			formService.serveProfileChange(req, user,userManager);
			formService.serveLike(req, authService, postManager);
			formService.setPostsProfilePage(req, user, postManager);
			 req.getRequestDispatcher("/WEB-INF/profilePage.jsp").forward(req, resp);
		}catch (Exception e) {
			errorDispatch(e.getMessage(), req, resp);
		}
       
    }
	
	/** Calculates the age of user from his birth date
	 * @param req recieved request
	 * @param user user whose profile i am loooking at
	 */
	private void setAge(HttpServletRequest req,User user) {
		if(user.getDate() != null) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy");
			int yearBirth = Integer.parseInt(df.format(user.getDate()));
			int yearNow = Integer.parseInt(df.format(new Date()));
			req.setAttribute("age", yearNow-yearBirth);
		}
	}
	

    private void errorDispatch(String err, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(ERR_ATTRIBUTE, err);
        req.getRequestDispatcher("/WEB-INF/profilePage.jsp").forward(req, resp);
    }
	
}
