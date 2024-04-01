package br.com.alveslf.springsecuritytest2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alveslf.springsecuritytest2.dtos.UserDtoReq;
import br.com.alveslf.springsecuritytest2.dtos.UserDtoRes;
import br.com.alveslf.springsecuritytest2.services.impl.UserServiceImpl;

@RestController
@RequestMapping("users")
public class UserController {
	
	@Autowired
	UserServiceImpl service;
	
	@PostMapping
	public UserDtoRes create(@RequestBody UserDtoReq data) {
		return service.save(data);
	}
	
	@GetMapping
	public String getOk() {
		return "OK";
	}
}
