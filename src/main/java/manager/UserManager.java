package manager;

import java.util.List;

import domain.User;
import domain.ValidationException;

/**
 */
public interface UserManager {

    /**
     * Method for authentication of user's credentials.
     *
     * @param username provided login
     * @param password provided password
     * @return true if username and password are a match, false otherwise
     */
    boolean authenticate(String username, String password);

    /**
     * Method for registering a new user.
     * @param newUser instance with new user data, expected not-null value
     * @throws UserValidationException if the new user data instance is not in valid state,
     *                                 e.g. required fields are missing
     */
    void register(User newUser) throws ValidationException;
    
    /** tries to return user with specified username
     * @param username of requested user
     * @return User with specified username or null if none found
     */
    User find(String username);
    
    /** tries to return user with specified id
     * @param id of requested user
     * @return User with specified if or null if none found
     */
    User findById(Long id);

	/** Method for fetching friends of specified user
	 * @param loggedUser whose friends we want to fetch
	 * @return friends of specified user
	 */
	List<User> fetchFriends(User loggedUser);
	
	/** Method for connecting two users with friendship relation
	 * @param user1 User to befriend
	 * @param user2 User to befriend
	 */
	void befriend(User user1, User user2);
	
	/** Method for fetching users that have substring in username
	 * @param sub substring to search for in names
	 * @return List of users with substring in name
	 */
	List<User> searchBySubstring(String sub);
	
	/** Method for fetching friend requests
	 * @param user whose requests we wnat to fetch
	 * @return list of requests that were sent to user
	 */
	List<User> fetchRequestsTo(User user);
	
	/** Method for sending friend request, requests are onedirectional
	 * @param to to whom send the request
	 * @param from from whom the request was sent
	 * @throws ValidationException if friend request was already sent in the past
	 */
	void sendFriendRequest(User to, User from) throws ValidationException;
	
	/**Mathod for changing users profile picture
	 * @param u whose picture to change
	 * @param profileImage name of the profile picture in img/profile/
	 */
	void changeProfileImage(User u, String profileImage);
}
