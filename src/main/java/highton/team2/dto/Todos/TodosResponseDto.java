package highton.team2.dto.Todos;

import highton.team2.dto.Todos.TodoItemDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TodosResponseDto {
    private Long id;
    private String userId;
    private String goal;
    private LocalDate endDate;
    private List<TodoItemDto> todos;

    // Constructor, Getters and Setters
}