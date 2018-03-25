package web.servlet;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import manager.UserManager;
import web.service.FormService;

/**
 * Servlet handling user registration requests.
 * listens at /register
 */
public class Register extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String USERNAME_PARAMETER = "username";
    private static final String EMAIL_PARAMETER = "email";
    private static final String DATE_PARAMETER = "date";
    private static final String TEST_PARAMETER = "test";
    private static final String GENDER_PARAMETER = "gender";
    
    private static final String ERROR_ATTRIBUTE = "err";

    private UserManager userManager;
    private FormService formService;

    public Register(UserManager userManager, FormService formService) {
        this.userManager = userManager;
        this.formService = formService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/registerPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
        	formService.validateRegistration(req, resp, userManager);
        }catch (Exception e) {
			errorDispatch(e.getMessage(), req, resp);
		}
    }

    private void errorDispatch(String err, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(ERROR_ATTRIBUTE, err);
        req.setAttribute(USERNAME_PARAMETER, req.getParameter(USERNAME_PARAMETER));
        req.setAttribute(EMAIL_PARAMETER, req.getParameter(EMAIL_PARAMETER));
        req.setAttribute(DATE_PARAMETER, req.getParameter(DATE_PARAMETER));
        req.setAttribute(TEST_PARAMETER, req.getParameter(TEST_PARAMETER));
        req.setAttribute(GENDER_PARAMETER, req.getParameter(GENDER_PARAMETER));
        req.getRequestDispatcher("/WEB-INF/registerPage.jsp").forward(req, resp);
    }
}
