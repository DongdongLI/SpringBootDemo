package com.dli.todolist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.dli.todolist.model.TodoList;
import com.dli.todolist.model.TodoListItem;
import com.dli.todolist.model.User;
import com.dli.todolist.repository.TodoListItemRepository;
import com.dli.todolist.repository.TodoListRepository;
import com.dli.todolist.request.TodoListItemRequest;
import com.dli.todolist.request.TodoListRequest;
import com.dli.todolist.response.TodoListCreatedResponse;
import com.dli.todolist.response.TodoListItemCreatedResponse;

@Controller
public class TodoListController {

	@Autowired
	private TodoListRepository todoListRepository;
	
	@Autowired
	private TodoListItemRepository todoListItemRepository;
	
	@PostMapping("/lists")
	public ResponseEntity<TodoListCreatedResponse> create(@RequestBody TodoListRequest todoListRequest, Authentication authentication) {
		//TodoList todoList = todoListRepository.save(TodoList.from(todoListRequest, getUserFromAuthentication(authentication)));
		TodoList todoList = todoListRepository.save(TodoList.from(todoListRequest));
		return new ResponseEntity<>(TodoListCreatedResponse.from(todoList), HttpStatus.CREATED);
	}
	
	@PostMapping("/lists/{id}/items")
	public ResponseEntity<TodoListItemCreatedResponse> createTodoListItem(@PathVariable("id") Long id
			, @RequestBody TodoListItemRequest todoListItemRequest) {
		TodoList todoList = todoListRepository.findTodoListById(id);
		TodoListItem todoListItem = todoListItemRepository.save(TodoListItem.from(todoListItemRequest, todoList));
		return new ResponseEntity<>(TodoListItemCreatedResponse.from(todoListItem), HttpStatus.CREATED);
	}
	
	private User getUserFromAuthentication(Authentication authentication) {
		return (User)authentication.getPrincipal();
	}
	
	@GetMapping("/lists")
	public ResponseEntity<Iterable<TodoList>> list() {
		List<TodoList> allLists = todoListRepository.findAll();
		return new ResponseEntity<>(allLists, HttpStatus.OK);
	}
	
	@GetMapping("/lists/{id}")
	public ResponseEntity<TodoList> get(@PathVariable("id") Long id) {
		return new ResponseEntity<>(todoListRepository.findTodoListById(id), HttpStatus.OK);
	}
	
	@DeleteMapping("/lists/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") Long id){
		todoListRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/lists/{id}/items/{itemId}")
	public ResponseEntity<String> delete(@PathVariable("id") Long id, @PathVariable("itemId") Long todoListItemId){
		TodoList todoList = todoListRepository.findTodoListById(id);
		todoListItemRepository.deleteTodoListItemByIdAndList(todoListItemId, todoList);
		return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/lists/{id}")
	public ResponseEntity<String> update(@PathVariable("id") Long id, @RequestBody TodoListRequest todoListRequest) {
		TodoList todoList = todoListRepository.findTodoListById(id);
		todoList.update(todoListRequest);
		todoListRepository.save(todoList);
		return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("lists/{id}/items/{itemId}")
	public ResponseEntity<String> updateItem(@PathVariable("id") Long id, 
			@PathVariable("itemId") Long itemId,
			@RequestBody TodoListItemRequest request) {
		TodoList todoList = todoListRepository.findTodoListById(id);
		TodoListItem todoListItem = todoListItemRepository.findTodoListItemByIdAndList(itemId, todoList);
		todoListItem.update(request);
		todoListItemRepository.save(todoListItem);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
