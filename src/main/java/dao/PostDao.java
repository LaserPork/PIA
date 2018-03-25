package dao;

import java.util.List;

import domain.Post;
import domain.User;


public interface PostDao extends GenericDao<Post> {

   
    /**Returns all posts from DB, use for unsigned homepage
     * @param page Number of page to show
     * @param pagination How many posts on a page
     * @return List of size pagination of posts
     */
    List<Post> findAll(int page, int pagination);
    /**Returns posts of defined user from DB, used for signed profile page
     * @param page Number of page to show
     * @param pagination How many posts on a page
     * @return List of size pagination of posts
     */
    List<Post> findUsers(User user, int page, int pagination);
    /**Returns posts of user and his friends from DB, use for signed homepage
     * @param page Number of page to show
     * @param pagination How many posts on a page
     * @return List of size pagination of posts
     */
    List<Post> findUsersAndFriends(User user, int page, int pagination);
	/** Returns list of users that liked specified post
	 * @param post specified post
	 * @param button String first,second or third defining which reaction
	 * @return Returns list of users that reacted to specified post with specified reaction
	 */
	List<User> getLikingUsers(Post post, String button);
	/** Returns post specified by id
	 * @param id Parameter of post we want to return
	 * @return Post with specified id
	 */
	Post findById(Long id);
	/** Used for determining how many pages there are for unsigned homepage
	 * @return Count of posts returned by findAll
	 */
	int findAllCount();
	/** Used for determining how many pages there are for user profile
	 * @param user whose post count should be returned
	 * @return post count
	 */
	int findUsersCount(User user);
	/** Used for determining how many pages there are for signed homepage
	 * @param user
	 * @return
	 */
	int findUsersAndFriendsCount(User user);
}
