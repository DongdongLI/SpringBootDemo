package com.dli.todolist.authorization;

import org.springframework.stereotype.Service;

import com.dli.todolist.model.User;

@Service
public interface UserService {
	void save(User user);
	
	User findUserByName(String name);
}
