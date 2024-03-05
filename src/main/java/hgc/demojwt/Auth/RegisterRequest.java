package hgc.demojwt.Auth;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @JsonProperty("username")
    private String username;
    
    @JsonProperty("password")
    private String password;
    
    @JsonProperty("firstname")
    private String firstname;
    
    @JsonProperty("lastname")
    private String lastname;
    
    @JsonProperty("country")
    private String country;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstname;
    }

    public String getLastName() {
        return lastname;
    }

    public String getCountry() {
        return country;
    }
}
