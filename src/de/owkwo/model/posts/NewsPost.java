package de.owkwo.model.posts;

import de.owkwo.model.profile.User;

/**
 * @author Jeffrey Groneberg A Post in the WkW-timeline
 * 
 */
public class NewsPost {

	private String id;
	private User author;
	private String postDate;
	private String message;

	public NewsPost(String id, User author, String postDate, String message) {
		super();
		this.id = id;
		this.author = author;
		this.postDate = postDate;
		this.message = message;
	}

	public String getId() {
		return id;
	}	

	public User getAuthor() {
		return author;
	}	

	public String getMessage() {
		return message;
	}	

	public String getPostDate() {
		return postDate;
	}

}
