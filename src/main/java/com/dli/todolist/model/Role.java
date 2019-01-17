package com.dli.todolist.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name="Role")
public class Role implements GrantedAuthority, Serializable{

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	
	@Override
	public String getAuthority() {
		return name;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == this)
			return true;
		if(obj == null || obj.getClass()!= getClass())
			return false;
		return Objects.equals(((Role)obj).name, this.name);
	}
	
	@Override
	public int hashCode() {
		return name.hashCode();
	}
}
