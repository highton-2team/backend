package highton.team2.dto.Todos;

import highton.team2.dto.Todos.TodoItemDto;
import lombok.Getter;
import java.time.LocalDate;
import java.util.List;

@Getter // Lombok 어노테이션을 사용하여 getter 메서드 자동 생성
public class TodosResponseDto {
    private LocalDate endDate;
    private List<TodoItemDto> todos;

    public TodosResponseDto(LocalDate endDate, List<TodoItemDto> todos) {
        this.endDate = endDate;
        this.todos = todos;
    }
}