package response;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.User;
import model.todo.TodoList;
import model.todo.TodoListItem;

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
