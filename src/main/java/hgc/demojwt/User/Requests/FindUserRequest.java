package hgc.demojwt.User.Requests;

public class FindUserRequest {

	private String usernameToSearch;
	private String usernameSearching;
	public FindUserRequest() {
		super();
	}
	public FindUserRequest(String usernameToSearch, String usernameSearching) {
		super();
		this.usernameToSearch = usernameToSearch;
		this.usernameSearching = usernameSearching;
	}
	public String getUsernameToSearch() {
		return usernameToSearch;
	}
	public void setUsernameToSearch(String usernameToSearch) {
		this.usernameToSearch = usernameToSearch;
	}
	public String getUsernameSearching() {
		return usernameSearching;
	}
	public void setUsernameSearching(String usernameSearching) {
		this.usernameSearching = usernameSearching;
	}
	
	
	
}
