package com.dli.todolist.authorization;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.dli.todolist.model.User;
import com.dli.todolist.repository.RoleRepository;
import com.dli.todolist.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	//@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	
	@Override
	public User findUserByName(String name) {
		return userRepository.findUserByEmail(name);
	}
	
	@Override
	public void save(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		//user.setPasswordConfirm(null);
		user.setRoles(new HashSet<>(roleRepository.findAll()));
		user.setAccountNonLocked(true);
		user.setEnabled(true);
		user.setCredentialsNonExpired(true);
		user.setAccountNonExpired(true);
		userRepository.save(user);
	}
}
