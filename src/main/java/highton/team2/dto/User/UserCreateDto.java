package highton.team2.dto.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateDto {
    private String userId;
    private String password;
    private String name;
    private Integer age;
    private String school;
    private List<String> license;
}
