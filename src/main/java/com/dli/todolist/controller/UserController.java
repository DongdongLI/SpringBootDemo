package com.dli.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.dli.todolist.authorization.SecurityService;
import com.dli.todolist.authorization.UserService;
import com.dli.todolist.authorization.UserValidator;
import com.dli.todolist.model.User;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private UserValidator userValidator;
	
	@GetMapping("/signup")
	public String signUp(Model model) {
		model.addAttribute("userForm", new User());
		return "signup";
	}
	
	@PostMapping("/signup")
	public String signUp(@ModelAttribute("userForm") User userForm, BindingResult result, Model model) {
			
		userValidator.validate(userForm, result);
		if(result.hasErrors()) {
			System.out.println(result.getAllErrors());
			return "signup";
		}
			
		userService.save(userForm);
		
		securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());
		
		return "redirect:/login";
	}
	
	@GetMapping("/login")
	public String login(Model model, String error, String logout) {
		if(error != null)
			model.addAttribute("error", "Username or passwrod is invalid.");
		
		if(logout != null)
			model.addAttribute("message", "You have been logged out successfully. ");
		
		return "login";
	}
}
