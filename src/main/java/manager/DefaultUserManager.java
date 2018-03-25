package manager;


import java.util.Arrays;
import java.util.List;



import dao.*;
import domain.*;
import utils.*;


public class DefaultUserManager implements UserManager {

    private UserDao userDao;
    private Encoder encoder;

    public DefaultUserManager(UserDao userDao, Encoder encoder) {
        this.userDao = userDao;
        this.encoder = encoder;
    }



	@Override
	public void register(User newUser) throws ValidationException {
		 if(!newUser.isNew()) {
	            throw new RuntimeException("User already exists, use save method for updates!");
	        }

	        newUser.validate();

	        User existinCheck = userDao.findByUsername(newUser.getUsername());
	        if(existinCheck != null) {
	            throw new ValidationException("Username already taken!");
	        }

	        newUser.setPassword(encoder.encode(newUser.getPassword()));

	        userDao.startTransaction();
	        try {
	            userDao.save(newUser);
	            userDao.commitTransaction();
	            userDao.refresh(newUser);
	        } catch (Exception e) {
	            userDao.rollbackTransaction();
	        }
	        
	}



	@Override
	public boolean authenticate(String username, String password) {
		 User u = userDao.findByUsername(username);
	     return u != null && encoder.validate(password, u.getPassword());
	}



	@Override
	public User find(String username) {
		return userDao.findByUsername(username);
	}



	@Override
	public User findById(Long id) {
		return userDao.findById(id);
	}



	@Override
	public List<User> fetchFriends(User loggedUser) {
		return userDao.findFriends(loggedUser);
	}



	@Override
	public void befriend(User user1, User user2) {
		userDao.startTransaction();
			user1.addFriend(user2);
			user2.addFriend(user1);
			userDao.save(user1);
			userDao.save(user2);
			userDao.refresh(user1);
			userDao.refresh(user2);
		userDao.commitTransaction();
	}



	@Override
	public List<User> searchBySubstring(String sub) {
		return userDao.findBySubstring(sub);
	}



	@Override
	public List<User> fetchRequestsTo(User user) {
		return userDao.findRequestsTo(user);
	}



	@Override
	public void sendFriendRequest(User to, User from) throws ValidationException {
		List<User> hisRequests = to.getRequestsTo();
		List<User> myRequests = from.getRequestsTo();
		System.out.println(to.toString() +" has "+ Arrays.toString(hisRequests.toArray()));
		System.out.println(from.toString() +" has "+ Arrays.toString(myRequests.toArray()));
		if(myRequests.contains(to)) {
			throw new ValidationException("User already has your friend request");
		}else if(hisRequests.contains(from)){
			userDao.startTransaction();
			 try {
				to.removeRequestTo(from);
				to.addFriend(from);
				from.addFriend(to);
				userDao.save(to);
				userDao.save(from);
				userDao.commitTransaction();
		     } catch (Exception e) {
		    	 userDao.rollbackTransaction();
		     }
			 
		}else {
			 userDao.startTransaction();
		     try {
		    	 to.addRequestFrom(from);
		    	 userDao.save(to);
		    	 userDao.commitTransaction();
		     } catch (Exception e) {
		    	 userDao.rollbackTransaction();
		     }
		    
		}
       
	}



	@Override
	public void changeProfileImage(User u, String profileImage) {
	    u.setProfileImage(profileImage);

	    userDao.startTransaction();
	     try {
	    	 userDao.save(u);
	    	 userDao.commitTransaction();
	     } catch (Exception e) {
	    	 userDao.rollbackTransaction();
	     }
	}
	

}
