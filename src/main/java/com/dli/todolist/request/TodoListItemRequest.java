package com.dli.todolist.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
public class TodoListItemRequest {
	private boolean done;
	private String description;
	
	public TodoListItemRequest(String description) {
		this.description = description;
	}
}
