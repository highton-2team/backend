package highton.team2.repository;

import highton.team2.entity.TodoList;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TodoListRepository extends JpaRepository<TodoList, Long> {
    List<TodoList> findAllByTodosId(Long todosId);
    Optional<TodoList> findByTodosIdAndTodoIndex(Long todosId, int todoIndex);
    List<TodoList> findByTodosId(Long todosId, Sort sort);

}
