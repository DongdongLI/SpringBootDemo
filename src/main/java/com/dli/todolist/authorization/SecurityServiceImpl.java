package com.dli.todolist.authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.dli.todolist.model.MyUserDetailService;

@Service
public class SecurityServiceImpl implements SecurityService{
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private MyUserDetailService userDetailService;
	
	@Override
	public void autoLogin(String username, String password) {
		UserDetails userDetail = userDetailService.loadUserByUsername(username);
		UsernamePasswordAuthenticationToken authenticationToken = 
				new UsernamePasswordAuthenticationToken(userDetail, 
						password, userDetail.getAuthorities());
		
		authenticationManager.authenticate(authenticationToken);
		if(authenticationToken.isAuthenticated()) {
			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			System.out.println("\nAuto sign in successfully.\n");
		}
	}
	
	@Override
	public void login(String username, String password) {
		UserDetails userDetail = userDetailService.loadUserByUsername(username);
		UsernamePasswordAuthenticationToken authenticationToken = 
				new UsernamePasswordAuthenticationToken(userDetail, 
						password, userDetail.getAuthorities());
		
		authenticationManager.authenticate(authenticationToken);
		if(authenticationToken.isAuthenticated()) {
			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			System.out.println("\nLogin successfully.\n");
		}
	}
	
	@Override
	public String findLoggedInUsername() {
		Object userDetail = SecurityContextHolder.getContext().getAuthentication()
				.getDetails();
		if(userDetail instanceof UserDetails)
			return ((UserDetails)userDetail).getUsername();
		
		return null;
	}
}
