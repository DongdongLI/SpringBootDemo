package repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import model.User;
import model.todo.TodoList;
import model.todo.TodoListItem;

public interface TodoListItemRepository extends CrudRepository<TodoListItem, Long> {
	
	//TodoListItem findTodoListItemByIdAndListAndUser(Long id, TodoList list, User user);
	TodoListItem findTodoListItemByIdAndList(Long id, TodoList list);

//	@Transactional
//	void deleteTodoListItemByIdAndListAndUser(Long id, TodoList list, User user);
	@Transactional
	void deleteTodoListItemByIdAndList(Long id, TodoList list);
}