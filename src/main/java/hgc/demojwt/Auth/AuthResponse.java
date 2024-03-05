package hgc.demojwt.Auth;

import org.springframework.web.bind.annotation.ResponseBody;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@ResponseBody
public class AuthResponse {
    String token;

    public AuthResponse(String token) {
        this.token = token;
    }

	public String getToken() {
		// TODO Auto-generated method stub
		return this.token;
	}
}
