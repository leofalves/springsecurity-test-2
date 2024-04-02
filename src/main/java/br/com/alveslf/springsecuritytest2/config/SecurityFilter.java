package br.com.alveslf.springsecuritytest2.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.alveslf.springsecuritytest2.models.User;
import br.com.alveslf.springsecuritytest2.repositories.UserRepository;
import br.com.alveslf.springsecuritytest2.services.AuthenticationService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter{

	@Autowired
	private AuthenticationService authenticationService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		var token = extractTokenHeader(request);		
		if(token != null) {
			String username = authenticationService.checkToken(token);
			User user = userRepository.findByUsername(username);
			
			var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());			
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		
		filterChain.doFilter(request, response);
		
	}
	
	public String extractTokenHeader(HttpServletRequest request) {
		
		var authHeader = request.getHeader("Authorization");
		
		if (authHeader == null) {
			return null;
		}
		
		if(!authHeader.split(" ")[0].equals("Bearer")) {
			return null;
		}
		
		return authHeader.split(" ")[1];
	}

}
