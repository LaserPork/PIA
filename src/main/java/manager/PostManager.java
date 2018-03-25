package manager;

import java.util.List;


import domain.Post;
import domain.User;
import domain.ValidationException;

/**
 * Manages jobs that use posts
 */
public interface PostManager {

   

    /** Tries to create specified post
     * @param newPost To be created
     * @throws ValidationException If post has no content
     */
    void post(Post newPost) throws ValidationException;
    
    /** Uses dao to fetch all posts on a page
     * @param page which page of posts to fetch
     * @param pagination how many posts on a page
     * @return list of posts on a specified page
     */
    List<Post> fetchAll(int page, int pagination);
    
    /**Uses dao to fetch posts of a user on a page
     * @param user Which users posts i want to fetch
     * @param page which page of posts to fetch
     * @param pagination how many posts on a page
     * @return list of posts on a specified page
     */
    List<Post> fetchUsers(User user,int page, int pagination);
    
    /**Uses dao to fetch posts of a user and his friends on a page
     * @param user Which users posts i want to fetch
     * @param page which page of posts to fetch
     * @param pagination how many posts on a page
     * @return list of posts on a specified page
     */
    List<Post> fetchUsersAndFriends(User user,int page, int pagination);
    
    /** Used to find out how many posts there are
     * @return number of posts
     */
    int fetchAllCount();
 
    /**Used to find out how many posts specified user has
     * @param user Whose post count we want to fetch
     * @return post count of specified user
     */
    int fetchUsersCount(User user);
    

    /**Used to find out how many posts specified user and his friends have
     * @param user Whose post count we want to fetch
     * @return post count of specified user and his friends
     */
    int fetchUsersAndFriendsCount(User user);
    
    /** Reacts to a specified post with reaction first, second or third
     * @param user Who wants to like
     * @param post What he want to like
     * @param button Reaction out of enum["first","second","third"]
     */
    void likePost(User user, Post post, String button);
   
    
	/** Returns post with specified id
	 * @param id of requested post
	 * @return requested post or null if none found
	 */
	public Post findById(Long id);
	
	}
