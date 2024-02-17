package highton.team2.controller;

import highton.team2.dto.User.UserCreateDto;
import highton.team2.entity.User;
import highton.team2.service.UserService;
import highton.team2.util.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserCreateDto userCreateDto) {
        if (userService.findUserByUserId(userCreateDto.getUserId()) != null) {
            return Result.of(HttpStatus.FORBIDDEN, "이미 존재하는 아이디입니다.");
        }
        userService.createUser(userCreateDto);
        return Result.of(HttpStatus.OK, userCreateDto.getUserId());
    }
}