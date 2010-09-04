package de.owkwo.service.delegate.interfaces;

import java.util.ArrayList;

import de.owkwo.model.posts.ParentNewsPost;

public interface WKWServiceDelegatable {
	
	public final static String STATUS_SUCCESSFUL = "successful";
	public final static String STATUS_ERROR = "error";
	
	public void onPost(ArrayList<ParentNewsPost> posts, String status );
	
	public void onComment(ArrayList<ParentNewsPost> posts, String status );
	
	public void onLogIn(String status);
	
	public void onLogOut(String status);
	
	public void onNewsPageUpdate(ArrayList<ParentNewsPost> posts, String status, int page );	
	

}
