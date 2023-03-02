package com.byndr.boot.resource;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.byndr.boot.exceptionhandling.UserNotFoundException;
import com.byndr.boot.exceptionhandling.WrongInputException;
import com.byndr.boot.model.User;
import com.byndr.boot.service.UserDaoService;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
@RequestMapping("/rest")
public class UserResource {

	@Autowired
	UserDaoService service;
	
	@GetMapping("/users")
	public MappingJacksonValue retrieveAllUsers() {
		List<User> list = service.findAll(); 
		//Dynamic filtering of rest service// to send only name ,birth
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("name","birth");
		FilterProvider filters = new SimpleFilterProvider().addFilter("UserFilter",filter);
		MappingJacksonValue mapping = new MappingJacksonValue(list);
		System.out.println("/users API executed !!");
		mapping.setFilters(filters);
		return mapping ;
	}
	
	@GetMapping("/users/{id}")
	public User retrieveUser(@PathVariable int id) {		
		User user=service.findOne(id);
		if(user==null)
			throw new UserNotFoundException("student id : "+id+" not found");
		return user;
	}
	
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser=service.save(user);
		if(user.getName()==null)
			throw new WrongInputException("Enter sid,sname,birthdate properly");
		
		URI location=ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();

	
	}
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		
		User user=service.deleteOne(id);
		if(user==null)
			throw new UserNotFoundException("student id : "+id+" not found");
			}
}
