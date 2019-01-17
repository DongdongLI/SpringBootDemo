package com.dli.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.dli.todolist.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

}
