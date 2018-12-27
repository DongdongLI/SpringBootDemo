package repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import model.User;
import model.todo.TodoList;

public interface TodoListRepository extends CrudRepository<TodoList, Long>{
	//List<TodoList> findAllByUser(User user);
	
	List<TodoList> findAll();
	
//	TodoList findTodoListByIdAndUser(Long id, User user);
	
	TodoList findTodoListById(Long id);
	
//	@Transactional
//	void deleteByIdAndUser(Long id, User user);
	
	@Transactional
	void deleteById(Long id);
}
