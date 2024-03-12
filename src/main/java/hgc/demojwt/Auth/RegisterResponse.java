package hgc.demojwt.Auth;

public class RegisterResponse {
	String message;
	boolean register = false;
	
	public RegisterResponse() {
		super();
	}

	public RegisterResponse(String message, boolean register) {
		super();
		this.message = message;
		this.register = register;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isRegister() {
		return register;
	}

	public void setRegister(boolean register) {
		this.register = register;
	}

}
