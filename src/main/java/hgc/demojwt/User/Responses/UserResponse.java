package hgc.demojwt.User.Responses;

public class UserResponse {
    String message;
    String token;

	public UserResponse() {
		super();
	}

	public UserResponse(String message, String token) {
		super();
		this.message = message;
		this.token = token;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
