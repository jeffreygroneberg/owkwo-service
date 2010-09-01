package de.owkwo.model.profile;

/**
 * @author Jeffrey Groneberg
 * 
 * A User within the WkW-Network
 *
 */

public class User {
	
	public User(String id, String name, String avatarUrl) {
	
		super();
		this.id = id;
		this.name = name;
		this.avatarUrl = avatarUrl;
		
	}

	/**
	 * id given by the network
	 */
	private String id;
	
	/**
	 * Name of the user 
	 */
	private String name;
	
	/**
	 * Url of the avatar picture 
	 */
	private String avatarUrl;
	
	
	public String getId() {
		return id;
	}	

	public String getName() {
		return name;
	}
	
	
	public String getAvatarUrl() {
		return avatarUrl;
	}
	

	

}
