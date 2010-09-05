package de.owkwo.service.delegate.interfaces;

import java.util.ArrayList;

import de.owkwo.model.posts.NewsPost;
import de.owkwo.model.posts.ParentNewsPost;

public interface WKWServiceDelegatable {
	
	public final static String STATUS_SUCCESSFUL = "successful";
	public final static String STATUS_ERROR = "error";
	
	public void onPost(String status );
	
	
	public void onLogIn(String status);
	
	public void onLogOut(String status);
	
	public void onNewsPageUpdate(ArrayList<ParentNewsPost> posts, String status, int page );	
	
	public void onCommentForPost(ArrayList<NewsPost> newsPosts, String userId, String postId, int page, String status);
	
	public void onProfileHTML(String html, String status);
	

}
