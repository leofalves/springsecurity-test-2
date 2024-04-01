package br.com.alveslf.springsecuritytest2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.alveslf.springsecuritytest2.dtos.AuthDto;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public String auth(@RequestBody AuthDto authDto) {
		
		var userAuthenticationToken = new UsernamePasswordAuthenticationToken(authDto.username(), authDto.password());
		authenticationManager.authenticate(userAuthenticationToken);
		
		return "Token___AAAAABBBBBB";
	}
}
