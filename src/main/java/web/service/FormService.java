package web.service;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.lang3.RandomStringUtils;

import domain.Post;
import domain.User;
import domain.ValidationException;
import manager.PostManager;
import manager.UserManager;
import web.auth.AuthenticationService;

/**
 * Wrapper around HttpSession providing functionality for POST forms.
 *	Methods that serve POST requests are here

 */
public class FormService {

   
    public FormService() {
        
    }

    private static final String USERNAME_PARAMETER = "username";
    private static final String PASSWORD_PARAMETER = "password";
    private static final String CONFIRM_PWD_PARAMETER = "password_confirm";
    private static final String EMAIL_PARAMETER = "email";
    private static final String DATE_PARAMETER = "date";
    private static final String TEST_PARAMETER = "test";
    private static final String GENDER_PARAMETER = "gender";
	private static final String PAGE_PARAMETER = "page";
	private static final String PAGINATION_PARAMETER = "pagination";
	private static final String CONTENT_PARAMETER = "postContent";
	private static final String FRIENDREQUEST_PARAMETER = "request";
	private static final String SEARCH_PARAMETER = "search";
	private static final String LOGOUT_PARAMETER = "logout";
	 
 
	  

	  
  

	/**Serves users action, reacting to post
	 * @param req recieved post request
	 * @param authService service for determining login state
	 * @param postManager used for finding the post to react to
	 * @throws ValidationException If something goes wrong, propagates message to the user
	 */
	public void serveLike(HttpServletRequest req, AuthenticationService authService, PostManager postManager) 
			throws ValidationException {
		if(req.getParameter("1") != null||req.getParameter("2") != null||req.getParameter("3") != null) {
			if(authService.isLoggedIn(req.getSession())) {
				Long postId = null;
				Post post = null;
				User user = authService.getLoggedUser(req.getSession());
				try {
					postId = Long.parseLong(req.getParameter("likedPost"));
				} catch (Exception e) {
					throw new ValidationException("Post could not be found");
				}
				post = postManager.findById(postId);
				if(post == null) {
					throw new ValidationException("Post could not be found");
				}
				if(req.getParameter("1") != null) {
					postManager.likePost(user,post,"first");
				}else if(req.getParameter("2") != null) {
					postManager.likePost(user,post,"second");
				}else if(req.getParameter("3") != null) {
					postManager.likePost(user,post,"third");
				}else {
					throw new ValidationException("Something went wrong with reacting to that post");
				}
			
			
			}else {
				throw new ValidationException("You must be logged in to react to posts");
			}
		}
	}
	

	/** Determines whose profile i am looking at
	 * @param req recieved post request
	 * @param authService service for determining if i am logged in or not
	 * @param userManager manager for getting the user from model
	 * @return User that owns profile i am looking at
	 * @throws ValidationException Propagates info about failing to find specified user
	 */
	public User getUserOfProfile(HttpServletRequest req, AuthenticationService authService, UserManager userManager) throws ValidationException{
		Long userId = null;
		User user = null;
		try {
			userId = Long.parseLong(req.getPathInfo().substring(1));
		} catch (Exception e) {}
		if(userId == null) {
			if(authService.isLoggedIn(req.getSession())) {
				user = userManager.find((String)req.getSession().getAttribute("user")); 
				
				return user;
			}else {
				throw new ValidationException("User could not be found");
			}
		}else {
			user = userManager.findById(userId); 
			if(user != null) {
				
				return user;
			}else {
				throw new ValidationException("User could not be found");
			}
		}
		
	}



	/** Serves changes in profile, only image change is currently supported
	 * @param req recieved post request
	 * @param user user who wants to change his profile
	 * @param userManager for propagating the profile change to model
	 * @throws IOException is thrown when post request wasnt multipart
	 * @throws ServletException is thrown when post request wasnt multipart
	 * @throws ValidationException propagates info to user when something goes wrong
	 */
	public void serveProfileChange(HttpServletRequest req, User user, UserManager userManager) throws IOException, ServletException, ValidationException {
		if (req.getContentType() != null && req.getContentType().toLowerCase().indexOf("multipart/form-data") > -1 ) {
			if(req.getPart("pic") != null) {
				Part part = req.getPart("pic");
				String contentDisp = part.getHeader("content-disposition");
			    String[] items = contentDisp.split(";");
			    String ext = "";
			    for (String s : items) {
			        if (s.trim().startsWith("filename")) {
			        	if(s.trim().startsWith("filename=\"\"")) {
			        		return;
			        	}
			        	ext = s.substring(s.indexOf(".") + 1, s.length()-1);
			        	break;
			        }
			    }
			    if(ext.isEmpty()){
			    	throw new ValidationException("Error: Profile picture was not uploaded");
			    }
			    String newName = RandomStringUtils.randomAlphanumeric(8)+"."+ext;
			    part.write(File.separator+"img"+File.separator+"profile"+File.separator+newName);
			   
			    userManager.changeProfileImage(user, newName);
			}
		}
	}


	/** Validates registration form and throws exception if something is not valid
	 * @param req recived post request
	 * @param resp for redirecting user if all goes well
	 * @param userManager for checking if user already exists
	 * @throws ValidationException propagates info to user
	 * @throws IOException is thrown when something goes wrong with picture
	 * @throws ServletException is thrown when something goes wrong with picture
	 */
	public void validateRegistration(HttpServletRequest req, HttpServletResponse resp, UserManager userManager) throws ValidationException, IOException, ServletException {
		String username = req.getParameter(USERNAME_PARAMETER).trim();
        String password = req.getParameter(PASSWORD_PARAMETER).trim();
        String confirmPwd = req.getParameter(CONFIRM_PWD_PARAMETER).trim();
        String email = req.getParameter(EMAIL_PARAMETER).trim();
        String test = req.getParameter(TEST_PARAMETER).trim();
        String gender = req.getParameter(GENDER_PARAMETER);
        boolean genderB = true;
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;

        if(!req.getParameter(DATE_PARAMETER).isEmpty()) {
        	try {
    			date = formatter.parse(req.getParameter(DATE_PARAMETER));
    			if(date.after(new Date())) {
    				throw new ValidationException("This date is from the future");
    			}
    		} catch (Exception e1) {
    			throw new ValidationException("Not a valid date. Must be in DD.MM.YYYY format.");
    		}
        }
        if(username.length()<5 || username.length()>20) {
        	throw new ValidationException("Username must be between 5 and 20 letters long");
        }
        if(password.length()<5 || password.length()>20) {
        	throw new ValidationException("Password must be between 5 and 20 letters long");
        }
        if(!Objects.equals(password, confirmPwd)) {
        	throw new ValidationException("The password and confirm password fields do not match!");
        }
        
        if(req.getParameter(GENDER_PARAMETER).isEmpty()) {
        	throw new ValidationException("Gender is required field");
        }else if(gender.equals("male")){
        	genderB = true;
        }else if(gender.equals("female")){
        	genderB = false;
        }else {
        	throw new ValidationException("Unsupported gender");
            
        }
        
        try{
        	 if(Integer.parseInt(test)!=4) {
             	throw new Exception();
             }
        }catch (Exception e) {
        	throw new ValidationException("Learn math! Square root of sixteen is four!");
		}
       
        
       
        	User newUser = new User(username, password, email, date, genderB);
            userManager.register(newUser);
            req.getSession().setAttribute("user", username);
            serveProfileChange(req, newUser, userManager);
            resp.sendRedirect(""); 
      
	}
	
	/** Saves user chosen pagination
	 * @param req recieved post request
	 */
	private void setPagination(HttpServletRequest req) {
		if(req.getParameter(PAGINATION_PARAMETER) != null) {
			req.setAttribute(PAGE_PARAMETER, "1");
			int pagination;
			try{
			pagination = Integer.parseInt(req.getParameter(PAGINATION_PARAMETER));
			}catch (Exception e) {
				pagination = 10;
			}	
			req.getSession().setAttribute("pagination", pagination);
		}
		if(req.getSession().getAttribute("pagination")==null) {
			req.getSession().setAttribute("pagination", 10);
		}
	}
	
	/** Sets posts to show using pagination
	 * @param req recieved post request
	 * @param authService for determining what posts to fetch
	 * @param postManager for getting the acutall posts
	 * @throws ValidationException propagates info to user
	 */
	public void setPostsHomePage(HttpServletRequest req, AuthenticationService authService, PostManager postManager) throws ValidationException {
		setPagination(req);
		String page = req.getParameter(PAGE_PARAMETER);
		int p = 1;
		if(page != null) {
			try {
				p = Integer.valueOf(page);
				if(p<0 || p>1000) {
					p = 1;
				}
			}catch (Exception e) {
				p = 1;
			}
			
		}
		int pagination = (Integer)req.getSession().getAttribute("pagination");
		if(authService.isLoggedIn(req.getSession())) {
			User u = authService.getLoggedUser(req.getSession());
			List<Post> posts = postManager.fetchUsersAndFriends(u,p, pagination);
			int pages = (int) Math.ceil((postManager.fetchUsersAndFriendsCount(u)/(double)pagination));
			req.setAttribute("posts", posts);
			req.setAttribute("pageCount", createPageArray(pages));
			if(posts == null) {
				throw new ValidationException("Welcome! Here you will see posts of you and your friends. Post something! Send some requests!");
			}
		}else {
			List<Post> posts = postManager.fetchAll(p,pagination);
			int pages = (int) Math.ceil((postManager.fetchAllCount()/(double)pagination));
			req.setAttribute("posts", posts);
			req.setAttribute("pageCount", createPageArray(pages));
			if(posts == null) {
				throw new ValidationException("There are no posts");
			}
		}
		
	}
	
	/**Creates page array for printing in JSP 
	 * @param pages how many pages there will be
	 * @return incremetning int array of pages size
	 */
	private int[] createPageArray(int pages) {
		int[] res = new int[pages];
		for (int i = 0; i < res.length; i++) {
			res[i] = i+1;
		}
		return res;
	}


	/**Serves posting request
	 * @param req recieved post request
	 * @param authService for determining if user can post
	 * @param postManager for adding the actual post
	 * @throws ValidationException propagating info to user
	 */
	public void servePost(HttpServletRequest req,AuthenticationService authService, PostManager postManager) throws ValidationException {
		String content = req.getParameter(CONTENT_PARAMETER);
		if(content != null) {
    		postManager.post(new Post(authService.getLoggedUser(req.getSession()), content));        		
    	}
	}


	/** Sets posts of profile page to show using pagination
	 * @param req recieved post request
	 * @param user for determining whose posts to fetch
	 * @param postManager for getting the acutall posts
	 * @throws ValidationException propagates info to user
	 */
	public void setPostsProfilePage(HttpServletRequest req, User user, PostManager postManager) throws ValidationException {
		setPagination(req);
		String page = req.getParameter(PAGE_PARAMETER);
		int p = 1;
		if(page != null) {
			try {
				p = Integer.valueOf(page);
				if(p<0 || p>1000) {
					p = 1;
				}
			}catch (Exception e) {
				p = 1;
			}
			
		}
		int pagination = (Integer)req.getSession().getAttribute("pagination");
		
		List<Post> posts = postManager.fetchUsers(user,p,pagination);
		int pages = (int) Math.ceil((postManager.fetchUsersCount(user)/(double)pagination));
		req.setAttribute("posts", posts);
		req.setAttribute("pageCount", createPageArray(pages));
		if(posts == null) {
			throw new ValidationException("Here you will see only your posts. Post something!");
		}
		
	}


	/** Serves friend request
	 * @param req recieved post request
	 * @param authService for determining who send the request
	 * @param userManager for determining to whom request was sent
	 * @throws ValidationException propagating info to user
	 */
	public void serveFriendRequest(HttpServletRequest req, AuthenticationService authService, UserManager userManager) throws ValidationException {
		String username = req.getParameter(FRIENDREQUEST_PARAMETER);
		if(username != null) {
			userManager.sendFriendRequest(userManager.find(username), authService.getLoggedUser(req.getSession()));
			throw new ValidationException("Friend request sent");
		}	
	}


	/** Serves search request
	 * @param req recieved post request
	 * @param authService determine who is searching
	 * @param userManager used to fetch users that were found
	 * @throws ValidationException propagates info to user
	 */
	public void serveSearch(HttpServletRequest req, AuthenticationService authService, UserManager userManager) throws ValidationException {
		String substring = req.getParameter(SEARCH_PARAMETER);
		if(substring == null){
			substring = "";
		}
        List<User> usersFound = userManager.searchBySubstring(substring);
        req.setAttribute("found", usersFound);
        if(usersFound.isEmpty()) {
        	throw new ValidationException("No users found");
        }
		
	}

	/**Serves logout request
	 * @param req recieved post request
	 * @return if there was a logout request and was successful returns true
	 */
	public boolean serveLogOut(HttpServletRequest req) {
		if(req.getParameter(LOGOUT_PARAMETER) != null) {
			req.getSession().invalidate();
			return true;
		}
		return false;
	}

    
}
