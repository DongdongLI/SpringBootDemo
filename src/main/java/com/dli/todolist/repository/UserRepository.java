package com.dli.todolist.repository;

import org.springframework.data.repository.CrudRepository;

import com.dli.todolist.model.User;

public interface UserRepository extends CrudRepository<User, Long>{

	User findUserByEmail(String email);
}
