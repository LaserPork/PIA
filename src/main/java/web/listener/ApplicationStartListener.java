package web.listener;

import java.io.File;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration.Dynamic;
import javax.servlet.annotation.WebListener;

import main.ApplicationContext;
import web.filter.AuthenticationGuard;
import web.filter.CodingFilter;
import web.servlet.*;


/**
 * Application startup listener. Handles registration of servlets
 * and injection of their dependencies.
 *
 */
@WebListener
public class ApplicationStartListener implements ServletContextListener {

    private ApplicationContext ctx;

  
    public void contextInitialized(ServletContextEvent sce) {
        ctx = new ApplicationContext();
        MultipartConfigElement mp = new MultipartConfigElement(System.getProperty("user.dir")+File.separator+"src"+File.separator+"main"+File.separator+"webapp",
        		1024 * 1024 * 10, 1024 * 1024 * 15, 1024 * 1024 * 1);
        
        sce.getServletContext().addServlet("home", new Main(ctx.getPostManager(),ctx.getAuthenticationService(), ctx.getFormService())).addMapping("/home");
        
        Dynamic profile = sce.getServletContext().addServlet("profile", new Profile(ctx.getPostManager(),ctx.getUserManager(),ctx.getAuthenticationService(), ctx.getFormService()));
        profile.addMapping("/profile","/profile/*");
        profile.setMultipartConfig(mp);
        
        Dynamic register = sce.getServletContext().addServlet("register", new Register(ctx.getUserManager(), ctx.getFormService()));
        register.addMapping("/register");
        register.setMultipartConfig(mp);
        
        sce.getServletContext().addServlet("friends", new Friends(ctx.getUserManager(),ctx.getAuthenticationService(), ctx.getFormService())).addMapping("/friends");
        
        sce.getServletContext().addServlet("requests", new Requests(ctx.getUserManager(),ctx.getAuthenticationService(), ctx.getFormService())).addMapping("/requests");
        
        sce.getServletContext().addServlet("login", new Login(ctx.getAuthenticationService())).addMapping("");
        
        sce.getServletContext().addServlet("search", new Search(ctx.getUserManager(),ctx.getAuthenticationService(), ctx.getFormService())).addMapping("/search");
        
        sce.getServletContext().addFilter("authFilter", new AuthenticationGuard(ctx.getAuthenticationService())).addMappingForUrlPatterns(null, false, "/search/*","/friends/*","/requests/*");
    
        sce.getServletContext().addFilter("codingFilter", new CodingFilter()).addMappingForUrlPatterns(null, false, "/*");
        
    }

    
    public void contextDestroyed(ServletContextEvent sce) {
        ctx.destroy();
    }
}
