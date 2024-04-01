package br.com.alveslf.springsecuritytest2.services;

import br.com.alveslf.springsecuritytest2.dtos.UserDtoReq;
import br.com.alveslf.springsecuritytest2.dtos.UserDtoRes;

public interface UserService {

	public UserDtoRes save(UserDtoReq userDto);
	
}
