
public class Follow {
	
	protected String user;
	protected boolean isFollowedByCurrentUser;
	
	public Follow(String user, boolean isFollowedByCurrentUser) {
		super();
		this.user = user;
		this.isFollowedByCurrentUser = isFollowedByCurrentUser;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public boolean getIsFollowedByCurrentUser() {
		return isFollowedByCurrentUser;
	}
	public void setFollowedByCurrentUser(boolean isFollowedByCurrentUser) {
		this.isFollowedByCurrentUser = isFollowedByCurrentUser;
	} 
	
	
	
	

}
