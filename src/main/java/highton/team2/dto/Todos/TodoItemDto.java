package highton.team2.dto.Todos;

import lombok.Getter;

@Getter // 여기에도 동일하게 적용
public class TodoItemDto {
    private int id;
    private String todo;
    private boolean completed;

    public TodoItemDto(int id, String todo, boolean completed) {
        this.id = id;
        this.todo = todo;
        this.completed = completed;
    }
}
