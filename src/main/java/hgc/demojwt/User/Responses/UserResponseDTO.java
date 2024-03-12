package hgc.demojwt.User.Responses;

import hgc.demojwt.User.DTO.UserDTO;

public class UserResponseDTO {
	String token;
	UserDTO userDTO;
	public UserResponseDTO() {
		super();
	}
	public UserResponseDTO(String token, UserDTO userDto) {
		super();
		this.token = token;
		this.userDTO = userDto;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public UserDTO getUserDto() {
		return userDTO;
	}
	public void setUserDto(UserDTO userDto) {
		this.userDTO = userDto;
	}
	
	
}
