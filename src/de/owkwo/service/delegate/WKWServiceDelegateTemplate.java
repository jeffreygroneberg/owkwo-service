/**
 * 
 */
package de.owkwo.service.delegate;

import java.util.ArrayList;

import de.owkwo.model.posts.NewsPost;
import de.owkwo.model.posts.ParentNewsPost;
import de.owkwo.service.delegate.interfaces.WKWServiceDelegatable;

/**
 * @author Jeffrey Groneberg
 *
 */
public class WKWServiceDelegateTemplate implements WKWServiceDelegatable {

	@Override
	public void onCreatePost(String status) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLogIn(String status) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLogOut(String status) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGetPostsForPage(ArrayList<ParentNewsPost> posts,
			String status, int page) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGetCommentForPost(ArrayList<NewsPost> newsPosts,
			String userId, String postId, int page, String status) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGetProfileHTML(String html, String status) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCreateCommentForPost(String userId, String postId,
			String status) {
		// TODO Auto-generated method stub
		
	}

	

}
