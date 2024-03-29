package hgc.demojwt.Auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = {"http://localhost:4200"})
@RequiredArgsConstructor
public class AuthController {
    
	@Autowired
	private AuthService authService;
    
    @PostMapping(value = "login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request)
    {
        return ResponseEntity.ok(authService.login(request));
    }

    
    @PostMapping(value = "register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request) {
       
    	RegisterResponse response = authService.register(request);

        return ResponseEntity.ok(response);
    }


}
