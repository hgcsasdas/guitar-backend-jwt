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

	public RegisterResponse register(RegisterRequest request) {
		 RegisterResponse registerResponse = new RegisterResponse();

		 System.out.println(registerResponse.toString());
	        // Validar la longitud de la contraseña
	        if (request.getPassword().length() < 8) {
	            registerResponse.setMessage("La contraseña debe tener al menos 8 caracteres");
	            return registerResponse;
	        }

	        // Verificar si el usuario ya existe
	        if (userRepository.existsByUsername(request.getUsername())) {
	            registerResponse.setMessage("El nombre de usuario ya está en uso");
	            return registerResponse;
	        }


	        User user = new User();
	        user.setUsername(request.getUsername());
	        user.setLastname(request.getLastName());
	        user.setFirstname(request.getFirstName());
	        user.setCountry(request.getCountry());
	        user.setPassword(passwordEncoder.encode(request.getPassword()));
	        user.setRole(Role.ROLE_ADMIN);

	        try {
	            userRepository.save(user);
	            registerResponse.setMessage("Usuario registrado correctamente");
	            registerResponse.setRegister(true);
	            return registerResponse;
	        } catch (Exception e) {
	            registerResponse.setMessage("Error al registrar el usuario, contacte al administrador");
	            return registerResponse;
	        }
	}

}
