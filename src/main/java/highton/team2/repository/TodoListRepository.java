package highton.team2.repository;

import highton.team2.entity.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoListRepository extends JpaRepository<TodoList, Long> {
    List<TodoList> findAllByTodosId(Long todosId);
}
