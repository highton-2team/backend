package highton.team2.dto.User;

import lombok.Data;

import java.util.List;

@Data
public class UserCreateDto {
    private String userId;
    private String password;
    private String name;
    private Integer age;
    private String school;
    private List<String> license;
}
