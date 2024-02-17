package highton.team2.dto.User;

import lombok.Data;

@Data
public class UserLoginDto {
    private String userId;
    private String password;

    // Constructors
    public UserLoginDto() {
    }

    public UserLoginDto(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }
}
