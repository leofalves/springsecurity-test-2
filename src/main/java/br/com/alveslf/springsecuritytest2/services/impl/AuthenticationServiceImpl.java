package br.com.alveslf.springsecuritytest2.services.impl;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import br.com.alveslf.springsecuritytest2.dtos.AuthDto;
import br.com.alveslf.springsecuritytest2.models.User;
import br.com.alveslf.springsecuritytest2.repositories.UserRepository;
import br.com.alveslf.springsecuritytest2.services.AuthenticationService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByUsername(username);
	}

	@Override
	public String obtainToken(AuthDto authDto) {
		User user = userRepository.findByUsername(authDto.username());
		return generateTokenJwt(user);
	}

	public String generateTokenJwt(User user) {
		try {
			Algorithm algorithm = Algorithm.HMAC256("my_fucking_secret");
			return JWT.create()
						.withIssuer("springsecurity-test-2")
						.withSubject(user.getUsername())
						.withExpiresAt(getExpiryDate())
						.sign(algorithm);
		} catch (JWTCreationException e) {
			throw new RuntimeException("Error while generating token: " + e.getMessage());

		}
	}

	private Instant getExpiryDate() {
		return LocalDateTime.now().plusHours(8).toInstant(ZoneOffset.of("-03:00"));
	}

}
