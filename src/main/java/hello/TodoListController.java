package hello;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import model.User;
import model.todo.TodoList;
import model.todo.TodoListItem;
import repository.TodoListItemRepository;
import repository.TodoListRepository;
import request.TodoListItemRequest;
import request.TodoListRequest;
import response.TodoListCreatedResponse;
import response.TodoListItemCreatedResponse;

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
}
