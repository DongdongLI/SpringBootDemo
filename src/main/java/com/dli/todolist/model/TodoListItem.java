package com.dli.todolist.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.dli.todolist.request.TodoListItemRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Data
@Getter
@Setter
@Table(name="todoListItem")
@ToString(exclude= {"list"})
public class TodoListItem {

	@Id
	@GeneratedValue
	private Long id;
	private String description;
	private boolean done;
	
	@ManyToOne
	@JoinColumn(name="todoListId")
	@JsonIgnore
	private TodoList list;
	
//	@OneToOne
//	@JoinColumn(name="userId")
//	@JsonIgnore
//	private User user;

//	public TodoListItem(String description, TodoList list, User user) {
//		this.description = description;
//		this.list = list;
//		this.user = user;
//	}
	
	public TodoListItem(String description, TodoList list) {
		this.description = description;
		this.list = list;
	}
	
	public static TodoListItem from(TodoListItemRequest todoListItemRequest, TodoList todoList) {
		return new TodoListItem(todoListItemRequest.getDescription(), todoList);
	}
	
	public void update(TodoListItemRequest request) {
		setDescription(request.getDescription());
		setDone(request.isDone());
	}
}
