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

	/**
	 * 
	 */
	public WKWServiceDelegateTemplate() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see de.owkwo.service.delegate.interfaces.WKWServiceDelegatable#onPost(java.util.ArrayList, java.lang.String)
	 */
	@Override
	public void onPost(String status) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see de.owkwo.service.delegate.interfaces.WKWServiceDelegatable#onComment(java.util.ArrayList, java.lang.String)
	 */
	

	/* (non-Javadoc)
	 * @see de.owkwo.service.delegate.interfaces.WKWServiceDelegatable#onLogIn(java.util.ArrayList, java.lang.String)
	 */
	@Override
	public void onLogIn(String status) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see de.owkwo.service.delegate.interfaces.WKWServiceDelegatable#onLogOut(java.lang.String)
	 */
	@Override
	public void onLogOut(String status) {
		// TODO Auto-generated method stub

	}

	

	@Override
	public void onNewsPageUpdate(ArrayList<ParentNewsPost> posts,
			String status, int page) {
		// TODO Auto-generated method stub
		
	}

	

	@Override
	public void onCommentForPost(ArrayList<NewsPost> newsPosts, String userId,
			String postId, int page,  String status) {
		// TODO Auto-generated method stub
		
	}

}
