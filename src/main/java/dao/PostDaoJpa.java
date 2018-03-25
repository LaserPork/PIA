package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import domain.Post;
import domain.User;



/**
 * @author Jakub
 *	Implementation of DAO accessing Post objects over JPA
 */
public class PostDaoJpa extends GenericDaoJpa<Post> implements PostDao {

	
    public PostDaoJpa(EntityManager em) {
        super(em, Post.class);
    }

   
    @Override
    public List<Post> findUsers(User user, int page, int pagination) {
        TypedQuery<Post> q = em.createQuery("SELECT p FROM Post p WHERE p.user.username = :uname ORDER BY p.timestamp DESC", Post.class);
        q.setParameter("uname", user.getUsername());
        q.setFirstResult((page-1)*pagination);
        q.setMaxResults(pagination);
        try {
            return q.getResultList();
        } catch (NoResultException e) {
            //no result found, ensuring the behaviour described by interface specification
            //see javadoc of the findByUsername method.
            return null;
        }
    }
    
    @Override
    public List<Post> findAll(int page, int pagination) {
        TypedQuery<Post> q = em.createQuery("SELECT p FROM Post p ORDER BY p.timestamp DESC", Post.class);
        q.setFirstResult((page-1)*pagination);
        q.setMaxResults(pagination);
        try {
            return q.getResultList();
        } catch (NoResultException e) {
            //no result found, ensuring the behaviour described by interface specification
            //see javadoc of the findByUsername method.
            return null;
        }
    }


	@Override
	public List<Post> findUsersAndFriends(User user, int page, int pagination) {
		 TypedQuery<Post> q = em.createQuery("SELECT p FROM Post p WHERE :uname MEMBER OF p.user.friends OR :uname = p.user ORDER BY p.timestamp DESC", Post.class);
	        q.setParameter("uname", user);
	        q.setFirstResult((page-1)*pagination);
	        q.setMaxResults(pagination);
	        try {
	            return q.getResultList();
	        } catch (NoResultException e) {
	            //no result found, ensuring the behaviour described by interface specification
	            //see javadoc of the findByUsername method.
	            return null;
	        }
	}


	@Override
	public List<User> getLikingUsers(Post post, String button) {
		TypedQuery<User> q;
		switch (button) {
		case "first":
			q = em.createQuery("SELECT u FROM Post p, User u JOIN p.first u WHERE p = :post AND u MEMBER OF p.first", User.class);
			break;
		case "second":
			q = em.createQuery("SELECT u FROM Post p, User u JOIN p.second u WHERE p = :post AND u MEMBER OF p.second", User.class);		
			break;
		case "third":
			q = em.createQuery("SELECT u FROM Post p, User u JOIN p.third u WHERE p = :post AND u MEMBER OF p.third", User.class);
			break;
		default:
			q = em.createQuery("SELECT u FROM Post p, User u JOIN p.first u WHERE p = :post AND u MEMBER OF p.first", User.class);
			break;
		}
		 
		 q.setParameter("post", post);
	        try {
	            return q.getResultList();
	        } catch (NoResultException e) {
	            //no result found, ensuring the behaviour described by interface specification
	            //see javadoc of the findByUsername method.
	            return null;
	        }
	}


	@Override
	public Post findById(Long id) {
		 TypedQuery<Post> q = em.createQuery("SELECT p FROM Post p WHERE p.id = :pid", Post.class);
	     q.setParameter("pid", id);
	     try {
	    	 return q.getSingleResult();
	     } catch (NoResultException e) {
	    	 return null;
	     }
	}


	@Override
	public int findAllCount() {
		 	Query q = em.createQuery("SELECT COUNT(p) FROM Post p");
	        try {
	            return ((Long)q.getSingleResult()).intValue();
	        } catch (NoResultException e) {
	            return 0;
	        }
	}


	@Override
	public int findUsersCount(User user) {
		  	Query q = em.createQuery("SELECT COUNT(p) FROM Post p WHERE p.user.username = :uname");
	        q.setParameter("uname", user.getUsername());
	        try {
	        	return ((Long)q.getSingleResult()).intValue();
	        } catch (NoResultException e) {
	            //no result found, ensuring the behaviour described by interface specification
	            //see javadoc of the findByUsername method.
	            return 0;
	        }
	}


	@Override
	public int findUsersAndFriendsCount(User user) {
		Query q = em.createQuery("SELECT COUNT(p) FROM Post p WHERE :uname MEMBER OF p.user.friends OR :uname = p.user");
        q.setParameter("uname", user);
        try {
        	return ((Long)q.getSingleResult()).intValue();
        } catch (NoResultException e) {
          
            return 0;
        }
	}

}
