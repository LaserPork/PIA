package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.StringUtils;

/**
 * Entity representing application User.

 */
@Entity
@Table(name = "vaverkaj_user")
public class User extends BaseObject {
    /**
     * Login, unique
     */
    private String username;
    /**
     * Secret for signing-in
     */
    private String password;
    private String email;
    private java.util.Date date;
    private String profileImage;
    
    
    private List<User> friendOf = new ArrayList<User>();
    private List<User> friends = new ArrayList<User>();
    
    private List<Post> posts = new ArrayList<Post>();
    
    private List<User> requestsFrom = new ArrayList<User>();
    private List<User> requestsTo = new ArrayList<User>();
    
    private boolean male;
	
	public boolean isMale() {
		return male;
	}

	public void setMale(boolean male) {
		this.male = male;
	}

	public User() {
    }

    public User(String username, String password, String email, java.util.Date date, boolean male) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.date = date;
        this.male = male;
    }

    /*
    ########### API ##################
     */

    /**
     * Validates that user instance is currently in a valid state.
     * @throws UserValidationException in case the instance is not in valid state.
     */
    public void validate() throws ValidationException {
        if(StringUtils.isBlank(username)) throw new ValidationException("Username is a required field");
        if(StringUtils.isBlank(password)) throw new ValidationException("Password is a required field");
        if(StringUtils.isBlank(email)) throw new ValidationException("Email can not be empty");
          
        Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        Matcher mat = pattern.matcher(email);
        if(!mat.matches()) throw new ValidationException("Not a valid email address");
           
    }

    /*
    ########### MAPPINGS #####################
     */
    
    
    

    @Column(unique = true)
    public String getUsername() {
        return username;
    }
 

	@ManyToMany
   	@JoinTable(
   		name="vaverkaj_requests",
   		joinColumns=@JoinColumn(name="toId"),
   		inverseJoinColumns=@JoinColumn(name="fromId")
   	)
   	public List<User> getRequestsFrom() {
   		return requestsFrom;
   	}
       

   	public void setRequestsFrom(List<User> requestsFrom) {
   		this.requestsFrom = requestsFrom;
   	}

   	@ManyToMany
   	@JoinTable(
   		name="vaverkaj_requests",
   		joinColumns=@JoinColumn(name="fromId"),
   		inverseJoinColumns=@JoinColumn(name="toId")
   	)
   	public List<User> getRequestsTo() {
   		return requestsTo;
   	}

   	public void setRequestsTo(List<User> requestsTo) {
   		this.requestsTo = requestsTo;
   	}


	@OneToMany(mappedBy = "user")
    public List<Post> getPosts() {
		return posts;
	}
    
    
    @ManyToMany
	@JoinTable(
		name="vaverkaj_friends",
		joinColumns=@JoinColumn(name="friendId"),
		inverseJoinColumns=@JoinColumn(name="personId")
	)
	public List<User> getFriends() {
		return friends;
	}
    
    public void addFriend(User friend) {
    	this.friends.add(friend);
    	friend.friendOf.add(this);
    }
    
    public void addFriendOf(User friend) {
    	this.friendOf.add(friend);
    	friend.friends.add(this);
    }
    
    public void removeFriend(User friend) {
    	this.friends.remove(friend);
    	friend.friendOf.remove(this);
    }
    
    public void removeFriendOf(User friend) {
    	this.friendOf.remove(friend);
    	friend.friends.remove(this);
    }
    
    public void addRequestTo(User friend) {
    	this.requestsTo.add(friend);
    	friend.requestsFrom.add(this);
    }
    
    public void addRequestFrom(User friend) {
    	this.requestsFrom.add(friend);
    	friend.requestsTo.add(this);
    }
    
    public void removeRequestTo(User friend) {
    	this.requestsTo.remove(friend);
    	friend.requestsFrom.remove(this);
    }
    
    public void removeRequestFrom(User friend) {
    	this.requestsFrom.remove(friend);
    	friend.requestsTo.remove(this);
    }
    
    
	@ManyToMany
	@JoinTable(
		name="vaverkaj_friends",
		joinColumns=@JoinColumn(name="personId"),
		inverseJoinColumns=@JoinColumn(name="friendId")
	)
	public List<User> getFriendOf() {
		return friendOf;
	}

	public void setFriendOf(List<User> friendOf) {
		this.friendOf = friendOf;
	}

	public void setFriends(List<User> friends) {
		this.friends = friends;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	
	public void addPost(Post post) {
		this.posts.add(post);
	}

	public void setUsername(String username) {
        this.username = username;
    }
    
    public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Temporal(TemporalType.DATE)
	public java.util.Date getDate() {
		return date;
	}

	public void setDate(java.util.Date date) {
		this.date = date;
	}

	public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        return !(username != null ? !username.equals(user.username) : user.username != null);

    }

    @Override
    public int hashCode() {
        return username != null ? username.hashCode() : 0;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("username='").append(username).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
