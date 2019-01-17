package com.dli.todolist.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.dli.todolist.request.TodoListRequest;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="todoList")
@Data
@Getter
@Setter
public class TodoList {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	
	@OneToMany(mappedBy="list",
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY)
	private List<TodoListItem> list = new ArrayList<>();
	
//	@OneToOne
//	@JoinColumn(name="userId")
//	private User user;
	
//	public TodoList(String name, User user) {
//		this.name = name;
//		this.user = user;
//	}
	
	public TodoList(String name) {
		this.name = name;
	}
	
//	public static TodoList from (TodoListRequest todoListRequest, User user) {
//		return new TodoList(todoListRequest.getName(), user);
//	}
	
	public static TodoList from (TodoListRequest todoListRequest) {
		return new TodoList(todoListRequest.getName());
	}
	
	public void update(TodoListRequest request) {
		setName(request.getName());
	}
}
