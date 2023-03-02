package com.byndr.boot.model;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFilter;

@Component
@JsonFilter("UserFilter")// rest dynamic filtering
//@JsonIgnoreProperties(value= {"name","birth"}) rest static filtering 
public class User {
	private int id;
	@Size(min=2, message="[enter more characters]")
	private String name;
	@Past(message="enter pastDate ")
	//@JsonIgnore   //for not sending birth in response rest static filtering
	private  Date birth;
	public User() {
		super();
	}
	public User(int id, String name, Date birth) {
		super();
		this.id = id;
		this.name = name;
		this.birth = birth;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birth=" + birth + "]";
	}
	public User(String name, Date birth) {
		super();
		this.name = name;
		this.birth = birth;
	}
	
	
}
