package com.byndr.boot.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.byndr.boot.model.User;

@Component
public class UserDaoService {
	
	private static List<User> users=new ArrayList<>();
	private static int userCount=3;
	static {
		users.add(new User(1,"Adam",new Date()));
		users.add(new User(2,"Rock",new Date()));
		users.add(new User(3,"Cena",new Date()));
	}
	
	public List<User> findAll(){
		return users;
	}
	
	public User save(User user) {
		if(user.getId()==0) {
			user.setId(++userCount);
		}
		users.add(user);
		return user;
	}
	
	public User findOne(int id) {
		for(User user : users) {
			if(user.getId()==id) 
				return user;
		}
		return null;
	}
	public User deleteOne(int id) {
		Iterator<User> iterator = users.iterator();
			while(iterator.hasNext()) {
				User user = iterator.next();
			if(user.getId()==id) {
				iterator.remove();
				return user;
			}
			}
		return null;
	}
	
}
