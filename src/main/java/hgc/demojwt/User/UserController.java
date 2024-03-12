package hgc.demojwt.User;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hgc.demojwt.User.Requests.FindUserRequest;
import hgc.demojwt.User.Requests.UserRequest;
import hgc.demojwt.User.Responses.UserResponse;
import hgc.demojwt.User.Responses.UserResponseDTO;
import hgc.demojwt.User.Responses.UserRoleResponse;

@RestController
@RequestMapping("api/v1/user")
@CrossOrigin(origins = { "http://localhost:4200" })
public class UserController {
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping(value = "/find-user")
	public ResponseEntity<UserResponseDTO> getUserByUsername(@RequestBody FindUserRequest findUserRequest) {
		UserResponseDTO userResponseDTO = userService.userIsUser(findUserRequest);

		if (userResponseDTO.getUserDto() == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(userResponseDTO);
	}

	@PostMapping(value = "/getRole")
	public ResponseEntity<UserRoleResponse> getUserRole(@RequestBody FindUserRequest findUserRequest) {
		UserRoleResponse userRoleResponse = userService.getUserRole(findUserRequest);

		return ResponseEntity.ok(userRoleResponse);

	}

	@PutMapping(value = "/update")
	public ResponseEntity<UserResponse> updateUser(@RequestBody UserRequest userRequest) {
		return ResponseEntity.ok(userService.updateUser(userRequest));
	}

}
