package com.dli.todolist.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dli.todolist.repository.UserRepository;

@Service
public class MyUserDetailService implements UserDetailsService{

	private UserRepository repository;
	
	@Autowired
	public MyUserDetailService(UserRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return repository.findUserByEmail(username);
	}

}
