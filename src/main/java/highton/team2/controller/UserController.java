package highton.team2.controller;

import highton.team2.dto.User.UserCreateDto;
import highton.team2.entity.User;
import highton.team2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/")
    public ResponseEntity<?> register(UserCreateDto userCreateDto) {
        User userByUserId = userService.findUserByUserId(userCreateDto.getUserId());
        if (userCreateDto.getUserId().isEmpty() || userByUserId != null) {
            return ResponseEntity.badRequest().body("값이 누락되었거나 잘못된 값이 있습니다.");
        }
        return ResponseEntity.ok(userCreateDto.getUserId());
    }
}
