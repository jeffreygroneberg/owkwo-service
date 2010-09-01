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

	private ArrayList<NewsPost> postThread;

	public ParentNewsPost(String id, User author, Date postDate,
			String message, ArrayList<NewsPost> postThread) {
		super(id, author, postDate, message);
		this.postThread = postThread;
	}

	public ArrayList<NewsPost> getPostThread() {
		return postThread;
	}

	public void setPostThread(ArrayList<NewsPost> postThread) {
		this.postThread = postThread;
	}
	

}
