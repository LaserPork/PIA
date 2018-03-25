package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import domain.User;


public class UserDaoJpa extends GenericDaoJpa<User> implements UserDao {

    public UserDaoJpa(EntityManager em) {
        super(em, User.class);
    }

   
    

	@Override
	public User findById(Long id) {
		 TypedQuery<User> q = em.createQuery("SELECT u FROM User u WHERE u.id = :uid", User.class);
	     q.setParameter("uid", id);
	     try {
	    	 return q.getSingleResult();
	     } catch (NoResultException e) {
	    	 return null;
	     }
	}




	@Override
	public User findByUsername(String username) {
		 TypedQuery<User> q = em.createQuery("SELECT u FROM User u WHERE u.username LIKE :uname", User.class);
	        q.setParameter("uname", username);
	        try {
	            return q.getSingleResult();
	        } catch (NoResultException e) {
	            //no result found, ensuring the behaviour described by interface specification
	            //see javadoc of the findByUsername method.
	            return null;
	        }
	}




	@Override
	public List<User> findFriends(User loggedUser) {
		 TypedQuery<User> q = em.createQuery("SELECT u FROM User u WHERE :uname MEMBER OF u.friends", User.class);
	     q.setParameter("uname", loggedUser);
	     try {
	         return q.getResultList();
	     } catch (NoResultException e) {
	         return null;
	     }
	}




	@Override
	public List<User> findBySubstring(String username) {
		TypedQuery<User> q = em.createQuery("SELECT u FROM User u WHERE u.username LIKE :uname", User.class);
        q.setParameter("uname", "%"+username+"%");
        try {
        	return q.getResultList();
        } catch (NoResultException e) {
            //no result found, ensuring the behaviour described by interface specification
            //see javadoc of the findByUsername method.
            return null;
        }
	}
	
	@Override
	 public List<User> findRequestsTo(User user) {
	        TypedQuery<User> q = em.createQuery("SELECT u FROM User u WHERE :uname MEMBER OF u.requestsTo", User.class);
	        q.setParameter("uname", user);
	        try {
	            return q.getResultList();
	        } catch (NoResultException e) {
	            //no result found, ensuring the behaviour described by interface specification
	            //see javadoc of the findByUsername method.
	            return null;
	        }
	    }
}
