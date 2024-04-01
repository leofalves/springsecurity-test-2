package br.com.alveslf.springsecuritytest2.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alveslf.springsecuritytest2.dtos.UserDtoReq;
import br.com.alveslf.springsecuritytest2.dtos.UserDtoRes;
import br.com.alveslf.springsecuritytest2.models.User;
import br.com.alveslf.springsecuritytest2.repositories.UserRepository;
import br.com.alveslf.springsecuritytest2.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository repository;
	
	@Override
	public UserDtoRes save(UserDtoReq userDto) {
		
		User userAlreadyExists = repository.findByUsername(userDto.username());
		
		if(userAlreadyExists != null) {
			throw new RuntimeException("User already exists");
		}
		
		User newUser = new User(userDto);
		repository.save(newUser);
		return new UserDtoRes(newUser.getId(), newUser.getName(), newUser.getUsername(), newUser.getPassword());
	}
}
