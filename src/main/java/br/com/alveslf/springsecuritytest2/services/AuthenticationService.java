package br.com.alveslf.springsecuritytest2.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import br.com.alveslf.springsecuritytest2.dtos.AuthDto;

public interface AuthenticationService extends UserDetailsService {
	
	public String obtainToken(AuthDto authDto);
	
	public String checkToken(String token);

}
