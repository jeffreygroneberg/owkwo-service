/**
 * 
 */
package de.owkwo.model.posts;

import java.util.ArrayList;

import de.owkwo.model.profile.User;

/**
 * @author Jeffrey Groneberg
 * 
 *
 */
public class ParentNewsPost extends NewsPost {

	private ArrayList<NewsPost> comments;

	public ParentNewsPost(String id, User author, String postDate,
			String message, ArrayList<NewsPost> comments) {
		super(id, author, postDate, message);
		this.comments = comments;
	}

	public ArrayList<NewsPost> getComments() {
		return comments;
	}

	public void setComments(ArrayList<NewsPost> comments) {
		this.comments = comments;
	}	
	

}
