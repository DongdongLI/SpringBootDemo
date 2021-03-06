package com.dli.todolist.response;

import com.dli.todolist.model.TodoListItem;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TodoListItemCreatedResponse {
	
	private String id;
	private String desc;
	
	public TodoListItemCreatedResponse(String id, String desc) {
		this.id = id;
		this.desc = desc;
	}
	
	public static TodoListItemCreatedResponse from (TodoListItem todoListItem) {
		return new TodoListItemCreatedResponse(todoListItem.getId().toString(), todoListItem.getDescription());
	}
}
