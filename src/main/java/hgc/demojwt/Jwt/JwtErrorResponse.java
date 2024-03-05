package hgc.demojwt.Jwt;

public class JwtErrorResponse {

	private String message;
	private boolean relogin;

	public JwtErrorResponse() {
		super();
	}

	public JwtErrorResponse(String message, boolean relogin) {
		super();
		this.message = message;
		this.relogin = relogin;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isRelogin() {
		return relogin;
	}

	public void setRelogin(boolean relogin) {
		this.relogin = relogin;
	}

}
