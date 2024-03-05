package hgc.demojwt.Auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import hgc.demojwt.Jwt.JwtService;
import hgc.demojwt.User.UserRepository;
import hgc.demojwt.User.Entitys.Role;
import hgc.demojwt.User.Entitys.User;

@Service
public class AuthService {

	private final UserRepository userRepository;
	private final JwtService jwtService;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;

	public AuthService(UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder,
			AuthenticationManager authenticationManager) {
		this.userRepository = userRepository;
		this.jwtService = jwtService;
		this.passwordEncoder = passwordEncoder;
		this.authenticationManager = authenticationManager;
	}

	public AuthResponse login(LoginRequest request) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		UserDetails user = userRepository.findByUsername(request.getUsername()).orElseThrow();
		String token = jwtService.getToken(user);

		AuthResponse auth = new AuthResponse(token);

		return auth;

	}

	public AuthResponse register(RegisterRequest request) {
		User user = new User();
		user.setUsername(request.getUsername());
		user.setLastname(request.getLastName());
		user.setFirstname(request.getFirstName());
		user.setCountry(request.getCountry());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setRole(Role.USER);

		userRepository.save(user);

		String token = jwtService.getToken(user);

		AuthResponse auth = new AuthResponse(token);

		return auth;
	}

}
