package com.programadorItaloBack.app.models.services;

import java.util.List;

import com.programadorItaloBack.app.controller.models.entity.User;

public interface IUserService {
	
	public List<User> findAll();
	
	public User FindByUserName(String userName);
	
	public void save(User user);
	
	public void delete(Long id);

}
