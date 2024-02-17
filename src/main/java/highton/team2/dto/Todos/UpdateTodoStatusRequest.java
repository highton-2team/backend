package highton.team2.dto.Todos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdateTodoStatusRequest {
    private int todoId;
    private boolean completed;
}
