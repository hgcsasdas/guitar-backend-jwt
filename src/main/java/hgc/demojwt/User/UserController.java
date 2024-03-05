package hgc.demojwt.User;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hgc.demojwt.User.DTO.UserDTO;
import hgc.demojwt.User.Requests.FindUserRequest;
import hgc.demojwt.User.Requests.UserRequest;
import hgc.demojwt.User.Responses.UserResponse;

@RestController
@RequestMapping("api/v1/user")
@CrossOrigin(origins = { "http://localhost:4200" })
public class UserController {
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping(value = "{id}")
	public ResponseEntity<UserDTO> getUser(@PathVariable Integer id) {
		UserDTO userDTO = userService.getUser(id);
		if (userDTO == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(userDTO);
	}
	
	@PostMapping(value = "/find-user")
	public ResponseEntity<UserDTO> getUserByUsername(@RequestBody FindUserRequest findUserRequest) {
		UserDTO userDTO = userService.userIsUser(findUserRequest);
				
		if (userDTO == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(userDTO);
	}
	
		
	
	@PutMapping(value = "/update")
	public ResponseEntity<UserResponse> updateUser(@RequestBody UserRequest userRequest) {
		return ResponseEntity.ok(userService.updateUser(userRequest));
	}
	
	
	
}
