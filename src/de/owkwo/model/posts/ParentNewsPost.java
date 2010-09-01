/**
 * 
 */
package de.owkwo.model.posts;

import java.util.ArrayList;
import java.util.Date;

import de.owkwo.model.profile.User;

/**
 * @author Jeffrey Groneberg
 * 
 *
 */
public class ParentNewsPost extends NewsPost {

	private ArrayList<NewsPost> comments;

	public ParentNewsPost(String id, User author, Date postDate,
			String message, ArrayList<NewsPost> comments) {
		super(id, author, postDate, message);
		this.comments = comments;
	}

	public ArrayList<NewsPost> getComments() {
		return comments;
	}	
	

}
