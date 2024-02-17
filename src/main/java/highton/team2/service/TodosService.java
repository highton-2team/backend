package highton.team2.service;

import highton.team2.dto.Todos.TodoItemDto;
import highton.team2.dto.Todos.TodosCreateDto;
import highton.team2.dto.Todos.TodosResponseDto;
import highton.team2.entity.TodoList;
import highton.team2.entity.Todos;
import highton.team2.repository.TodoListRepository;
import highton.team2.repository.TodosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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

    public TodosResponseDto getTodosByUserId(String userId) {
        return todosRepository.findByUserId(userId)
                .map(todos -> {
                    List<TodoItemDto> todoItems = todoListRepository.findAllByTodosId(todos.getId())
                            .stream()
                            .map(todoList -> new TodoItemDto(todoList.getTodoIndex(), todoList.getTodo(), todoList.isCompleted()))
                            .collect(Collectors.toList());
                    return new TodosResponseDto(todos.getEnd_date(), todoItems);
                }).orElseThrow(() -> new RuntimeException("설정된 목표가 없습니다."));
    }

    // TodosService 내부
    public String updateTodoCompletedStatus(String userId, int todoIndex, boolean completed) {
        Todos todos = todosRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("사용자의 Todos가 존재하지 않습니다."));

        TodoList todoList = todoListRepository.findByTodosIdAndTodoIndex(todos.getId(), todoIndex)
                .orElseThrow(() -> new RuntimeException("해당 Todo가 존재하지 않습니다."));

        todoList.setCompleted(completed);
        todoListRepository.save(todoList);

        return completed ? "정상적으로 완료 처리 되었습니다." : "정상적으로 미완료 처리 되었습니다.";
    }

    public boolean existsByUserId(String userId) {
        return todosRepository.findByUserId(userId).isPresent();
    }
}