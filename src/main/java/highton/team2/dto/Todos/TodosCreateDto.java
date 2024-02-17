package highton.team2.dto.Todos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodosCreateDto {
    private String goal;
    private Integer period;
    private Integer todos;
}
