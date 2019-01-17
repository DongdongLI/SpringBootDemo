package com.dli.todolist.request;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TodoListRequest {
	private String name;
	
	public TodoListRequest(String name) {
		this.name = name;
	}
}
