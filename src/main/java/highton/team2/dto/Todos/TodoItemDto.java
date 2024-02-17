package highton.team2.dto.Todos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TodoItemDto {
    private int id;
    private String todo;
    private boolean completed;
}
