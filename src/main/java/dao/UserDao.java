package dao;

import java.util.List;

import domain.User;


public interface UserDao extends GenericDao<User> {

    /**Searches for user with specified username
     *	
     * @param username the requested username
     * @return user with the given username or null
     */
    User findByUsername(String username);
    /**Searches for user with specified id
     * @param id the requested if
     * @return user with id or null
     */
    User findById(Long id);
	/**Searches for friends of specified user
	 * @param loggedUser whose friends i want to return
	 * @return List of friends or empty list if he has no firends
	 */
	List<User> findFriends(User loggedUser);
	/** Used when seaching for user 
	 * @param username whole name or substring of username
	 * @return List of users that have specified username or its substring in name
	 */
	List<User> findBySubstring(String username);
	/**Searches for friend requests of specified user 
	 * @param user Whose requests i want
	 * @return List of users who requested friendship or empty list if none had
	 */
	List<User> findRequestsTo(User user);
}
