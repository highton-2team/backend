package highton.team2.service;

import highton.team2.dto.Todos.TodosCreateDto;
import highton.team2.entity.TodoList;
import highton.team2.entity.Todos;
import highton.team2.repository.TodoListRepository;
import highton.team2.repository.TodosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class TodosService {
    private final TodosRepository todosRepository;
    private final TodoListRepository todoListRepository;

    public void createTodos(TodosCreateDto createDto, String userId) {
        LocalDate endDate = LocalDate.now().plusMonths(createDto.getPeriod());
        Todos todos = new Todos(null, userId, createDto.getGoal(), endDate, createDto.getTodos());
        todos = todosRepository.save(todos);

        createTodoLists(createDto.getTodos(), todos.getId());
    }

    private void createTodoLists(int count, Long todosId) {
        for (int i = 1; i <= count; i++) {
            String todo = "테스트 값 " + i;
            int todoIndex = i;
            TodoList todoList = new TodoList(null, todosId, todoIndex, todo, false);
            todoListRepository.save(todoList);
        }
    }

    public boolean existsByUserId(String userId) {
        return todosRepository.findByUserId(userId).isPresent();
    }
}