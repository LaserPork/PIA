package domain;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

/**
 * Entity representing application Post

 */
@Entity
@Table(name = "vaverkaj_post")
public class Post extends BaseObject {
    
	
	
    private User user;
   
	private String content;
    private List<User> first = new ArrayList<User>();
    private List<User> second = new ArrayList<User>();
    private List<User> third = new ArrayList<User>();
    private Timestamp timestamp;

    public Post() {
    	
    }

    public Post(User user, String content) {
        this.user = user;
        this.content = content;
    }

   
    public void validate() throws ValidationException {
    	if(user == null) throw new ValidationException("You must be logged in to make posts");
        if(StringUtils.isBlank(content)) throw new ValidationException("Your post must have a content");
    }

    /*
    ########### MAPPINGS #####################
     */


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post)) return false;

        Post post = (Post) o;

        return !(this.getId() != null ? !(this.getId() == post.getId()) : post.getId() != null);

    }

    @ManyToOne
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Lob
	@Column(length = 1000)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@ManyToMany
	@JoinTable(name="vaverkaj_postLikes_first")
	public List<User> getFirst() {
		return first;
	}

	public void setFirst(List<User>  first) {
		this.first = first;
	}

	public void addFirst(User user) {
		this.first.add(user);
	}
	
	public void removeFirst(User user) {
		this.first.remove(user);
	}
	
	@ManyToMany
	@JoinTable(name="vaverkaj_postLikes_second")
	public List<User> getSecond() {
		return second;
	}

	public void setSecond(List<User>  second) {
		this.second = second;
	}
	
	public void addSecond(User user) {
		this.second.add(user);
	}
	
	public void removeSecond(User user) {
		this.second.remove(user);
	}

	@ManyToMany
	@JoinTable(name="vaverkaj_postLikes_third")
	public List<User> getThird() {
		return third;
	}

	public void setThird(List<User> third) {
		this.third = third;
	}
	
	public void addThird(User user) {
		this.third.add(user);
	}
	
	public void removeThird(User user) {
		this.third.remove(user);
	}
	
	
	@Column(nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	

	@Override
    public int hashCode() {
        return content != null ? content.hashCode() : 0;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Post{");
        sb.append("["+user.toString()+"]").append(" Content: ").append(content);
        sb.append('}');
        return sb.toString();
    }
}
