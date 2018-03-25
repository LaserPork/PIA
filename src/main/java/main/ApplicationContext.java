package main;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import dao.*;
import manager.DefaultPostManager;

import manager.DefaultUserManager;
import manager.PostManager;

import manager.UserManager;
import utils.Encoder;
import utils.PasswordHashEncoder;
import web.auth.AuthenticationService;
import web.service.FormService;

/**
 * Application context holds references to all parts of the application,
 * manages their creation and provides them wherever needed.
 *
 */
public class ApplicationContext {

    //persistence
    private EntityManager em;
    private UserDao userDao;
    private PostDao postDao;

    //business
    private UserManager userManager;
   
    private PostManager postManager;
    private Encoder encoder;

    //web
    private AuthenticationService authenticationService;
    private FormService formService;

    public ApplicationContext() {
        em = Persistence.createEntityManagerFactory("pia_db").createEntityManager();
        userDao = new UserDaoJpa(em);
        postDao = new PostDaoJpa(em);
        encoder = new PasswordHashEncoder();
        userManager = new DefaultUserManager(userDao, encoder);
        postManager = new DefaultPostManager(postDao);
        authenticationService = new AuthenticationService(userManager);
        formService = new FormService();
    }

    public FormService getFormService() {
    	return formService;
    }
    
	public void destroy() {
        em.close();
    }

    public EntityManager getEm() {
        return em;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public UserManager getUserManager() {
        return userManager;
    }
    
    public PostDao getPostDao() {
        return postDao;
    }

    public PostManager getPostManager() {
        return postManager;
    }

    public Encoder getEncoder() {
        return encoder;
    }

    public AuthenticationService getAuthenticationService() {
        return authenticationService;
    }
}
