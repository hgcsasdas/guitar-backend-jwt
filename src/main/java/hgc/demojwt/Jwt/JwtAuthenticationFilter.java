package hgc.demojwt.Jwt;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	private final JwtService jwtService = new JwtService();
	
	private final UserDetailsService userDetailsService;
	
	private JwtAuthenticationFilter(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		final String token = getTokenFromRequest(request);
		final String username;
		
		if (token == null) {	
			filterChain.doFilter(request, response);
			return;
		}
		
		 try {
	            username = jwtService.getUsernameFromToken(token);
	            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
	                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
	                if (jwtService.isTokenValid(token, userDetails)) {
	                    UsernamePasswordAuthenticationToken authToken =
	                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	                    SecurityContextHolder.getContext().setAuthentication(authToken);
	                }
	            }
	        }  catch (ExpiredJwtException ex) {
	            response.setStatus(HttpStatus.UNAUTHORIZED.value());
	            JwtErrorResponse errorResponse = new JwtErrorResponse("Token expirado ", true);
	            String jsonErrorResponse = new ObjectMapper().writeValueAsString(errorResponse);
	            response.getWriter().write(jsonErrorResponse);
	            response.getWriter().flush();
	            return;
	        }


		
		filterChain.doFilter(request, response);
	}

	private String getTokenFromRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		
		if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
			return authHeader.substring(7);
		}
		
		return null;
	}

}
