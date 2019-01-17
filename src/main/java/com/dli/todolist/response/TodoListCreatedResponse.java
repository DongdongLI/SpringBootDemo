package com.dli.todolist.response;

import java.util.ArrayList;
import java.util.List;

import com.dli.todolist.model.TodoList;
import com.dli.todolist.model.TodoListItem;
import com.dli.todolist.model.User;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TodoListCreatedResponse {
	private String id;
	private String name;
	private List<TodoListItem> list = new ArrayList<>();

	public TodoListCreatedResponse(String id, String name) {
		
		this.id = id;
		this.name = name;
	}
	
	public static TodoListCreatedResponse from(TodoList todoList) {
		return new TodoListCreatedResponse(todoList.getId().toString(), todoList.getName());
	}
}
