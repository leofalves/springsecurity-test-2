package br.com.alveslf.springsecuritytest2.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.alveslf.springsecuritytest2.dtos.UserDtoReq;
import br.com.alveslf.springsecuritytest2.dtos.UserDtoRes;
import br.com.alveslf.springsecuritytest2.models.User;
import br.com.alveslf.springsecuritytest2.repositories.UserRepository;
import br.com.alveslf.springsecuritytest2.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	UserRepository repository;
	
	@Override
	public UserDtoRes save(UserDtoReq userDto) {
		
		User userAlreadyExists = repository.findByUsername(userDto.username());
		
		if(userAlreadyExists != null) {
			throw new RuntimeException("User already exists");
		}
		
		var passwordHash = passwordEncoder.encode(userDto.password());
		User newUser = new User(userDto.name(), userDto.username(), passwordHash, userDto.role());
		repository.save(newUser);
		
		return new UserDtoRes(newUser.getId(), newUser.getName(), newUser.getUsername(), newUser.getPassword(), newUser.getRole());
	}
}
