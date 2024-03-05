package hgc.demojwt.User;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hgc.demojwt.Jwt.JwtService;
import hgc.demojwt.User.DTO.UserDTO;
import hgc.demojwt.User.Entitys.Role;
import hgc.demojwt.User.Entitys.User;
import hgc.demojwt.User.Requests.FindUserRequest;
import hgc.demojwt.User.Requests.UserRequest;
import hgc.demojwt.User.Responses.UserResponse;

@Service
public class UserService {

	private final UserRepository userRepository;
	private final JwtService jwtService;

	public UserService(UserRepository userRepository, JwtService jwtService) {
		this.userRepository = userRepository;
		this.jwtService = jwtService;
	}

	@Transactional
	public UserResponse updateUser(UserRequest userRequest) {

		User user = new User(userRequest.getId(), userRequest.getUsername(), userRequest.getLastname(),
				userRequest.getFirstname(), userRequest.getCountry());

		userRepository.updateUser(user.getId(), user.getFirstname(), user.getLastname(), user.getCountry());

		UserDetails userDetails = userRepository.findByUsername(user.getUsername()).orElseThrow();
		String newToken = jwtService.getToken(userDetails);

		return new UserResponse("El usuario se actualizó satisfactoriamente", newToken);
	

	}

	public UserDTO getUser(Integer id) {
		User user = userRepository.findById(id).orElse(null);

		if (user != null) {
			UserDTO userDTO = new UserDTO(user.getId(), user.getUsername(), user.getFirstname(), user.getLastname(),
					user.getCountry());
			return userDTO;
		}
		return null;
	}

	public UserDTO searchUserByUsername(String username) {
		User user = userRepository.findByUsername(username).orElse(null);

		if (user != null) {
			UserDTO userDTO = new UserDTO(user.getId(), user.getUsername(), user.getFirstname(), user.getLastname(),
					user.getCountry());
			return userDTO;
		}
		return null;
	}

	public UserResponseDTO userIsUser(FindUserRequest findUserRequest) {
		Optional<Role> userRole = userRepository.findRoleByUsername(findUserRequest.getUsernameSearching());

		if (userRole.isPresent() && (userRole.get() == Role.ADMIN || (findUserRequest.getUsernameSearching().equals(findUserRequest.getUsernameToSearch())))) {
			
			//crear el token y devolver la respuesta

			UserDetails userDetails = userRepository.findByUsername(findUserRequest.getUsernameSearching()).orElseThrow();
			String newToken = jwtService.getToken(userDetails);

			UserDTO userDTO = searchUserByUsername(findUserRequest.getUsernameToSearch());

			return UserResponseDTO(newToken, userDTO);
		} 

		
		return null;
	}

}
