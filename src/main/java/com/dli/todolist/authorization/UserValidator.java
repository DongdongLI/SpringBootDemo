package com.dli.todolist.authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.dli.todolist.model.User;

@Component
public class UserValidator implements Validator{

	@Autowired
	private UserService userService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEMPTY");
		if(userService.findUserByName(user.getUsername()) != null)
			errors.reject("username", "This username is already taken");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEMPTY");		
//		if(userService.findUserByName(user.getName()) != null)
//			errors.reject("name", "This username is already taken");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEMPTY");
		if(!user.getPassword().equals(user.getPasswordConfirm()))
			errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
	}
}
