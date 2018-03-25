package manager;

import java.util.List;


import dao.*;
import domain.*;


public class DefaultPostManager implements PostManager {

    private PostDao postDao;
   
    public DefaultPostManager(PostDao postDao) {
        this.postDao = postDao;
      
    }



	@Override
	public void post(Post newPost) throws ValidationException {
		 if(!newPost.isNew()) {
	            throw new RuntimeException("Post already exists, use save method for updates!");
	        }

	        newPost.validate();

	        postDao.startTransaction();
	        try {
	            postDao.save(newPost);
	            postDao.commitTransaction();
	            postDao.refresh(newPost);
	        } catch (Exception e) {
	            postDao.rollbackTransaction();
	        }
	       
	}



	@Override
	public List<Post> fetchAll(int page, int pagination) {
		return postDao.findAll(page, pagination);
	}



	@Override
	public List<Post> fetchUsers(User user,int page, int pagination) {
		return postDao.findUsers(user, page, pagination);
	}



	@Override
	public List<Post> fetchUsersAndFriends(User user,int page, int pagination) {
		return postDao.findUsersAndFriends(user,page, pagination);
	}

	@Override
	public Post findById(Long id) {
		return postDao.findById(id);
	}

	@Override
	public void likePost(User user, Post post, String button) {
		List<User> alreadyLiked = postDao.getLikingUsers(post,button);
        postDao.startTransaction();
		if(!alreadyLiked.contains(user)) {
			switch (button) {
			case "first":
				post.addFirst(user);
				break;
			case "second":
				post.addSecond(user);
				break;
			case "third":
				post.addThird(user);
				break;
			default:
				break;
			}
		}else {
			switch (button) {
			case "first":
				post.removeFirst(user);
				break;
			case "second":
				post.removeSecond(user);
				break;
			case "third":
				post.removeThird(user);
				break;
			default:
				break;
			}
		}
		
		try {
			postDao.save(post);
	        postDao.commitTransaction();
        } catch (Exception e) {
            postDao.rollbackTransaction();
        }
	}
	


	@Override
	public int fetchAllCount() {
		return postDao.findAllCount();
	}



	@Override
	public int fetchUsersCount(User user) {
		return postDao.findUsersCount(user);
	}



	@Override
	public int fetchUsersAndFriendsCount(User user) {
		return postDao.findUsersAndFriendsCount(user);
	}



}
