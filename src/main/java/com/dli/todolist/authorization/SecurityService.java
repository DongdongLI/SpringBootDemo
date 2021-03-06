package com.dli.todolist.authorization;

public interface SecurityService {
	String findLoggedInUsername();
	
	void autoLogin(String username, String password);
	
	void login(String username, String password);
}
