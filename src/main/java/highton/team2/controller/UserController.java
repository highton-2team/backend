package highton.team2.controller;

import highton.team2.dto.User.UserCreateDto;
import highton.team2.dto.User.UserLoginDto;
import highton.team2.entity.User;
import highton.team2.service.UserService;
import highton.team2.util.Result;
import jakarta.servlet.http.HttpSession;
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

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDto loginDto, HttpSession session) {
        boolean isValidUser = userService.validateUserLogin(loginDto);
        if (isValidUser) {
            session.setAttribute("userId", loginDto.getUserId()); // 세션에 사용자 아이디 저장
            return Result.of(HttpStatus.OK, loginDto.getUserId());
        } else {
            return Result.of(HttpStatus.UNAUTHORIZED, "존재하지 않는 계정이거나 비밀번호가 잘못 되었습니다.");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserCreateDto userCreateDto) {
        if (userService.findUserByUserId(userCreateDto.getUserId()) != null) {
            return Result.of(HttpStatus.FORBIDDEN, "이미 존재하는 아이디입니다.");
        }
        userService.createUser(userCreateDto);
        return Result.of(HttpStatus.OK, userCreateDto.getUserId());
    }
}