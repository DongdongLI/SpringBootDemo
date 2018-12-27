package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import model.User;
import model.todo.TodoList;
import repository.TodoListItemRepository;
import repository.TodoListRepository;
import request.TodoListRequest;
import response.TodoListCreatedResponse;

@Controller
public class TodoListController {

	@Autowired
	private TodoListRepository todoListRepository;
	
	@Autowired
	private TodoListItemRepository todoListItemRepository;
	
	@PostMapping("/lists")
	public ResponseEntity<TodoListCreatedResponse> create(@RequestBody TodoListRequest todoListRequest, Authentication authentication) {
		TodoList todoList = todoListRepository.save(TodoList.from(todoListRequest, getUserFromAuthentication(authentication)));
		return new ResponseEntity<>(TodoListCreatedResponse.from(todoList), HttpStatus.CREATED);
	}
	
	private User getUserFromAuthentication(Authentication authentication) {
		return (User)authentication.getPrincipal();
	}
}
