package highton.team2.controller;

import highton.team2.dto.Todos.TodosCreateDto;
import highton.team2.dto.Todos.TodosResponseDto;
import highton.team2.service.TodosService;
import highton.team2.service.UserService;
import highton.team2.util.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/todolist")
@RequiredArgsConstructor
public class TodosController {

    private final TodosService todosService;
    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<?> registerTodos(@RequestBody TodosCreateDto createDto) {
        // 로그인 확인
        String userId = userService.getSessionUserId();
        if (userId == null) {
            return Result.of(HttpStatus.FORBIDDEN, "로그인 후 이용하시기 바랍니다.");
        }

        // 입력 값 검증
        if (createDto.getGoal() == null || createDto.getGoal().isEmpty() || createDto.getPeriod() == null || createDto.getTodos() == null) {
            return Result.of(HttpStatus.BAD_REQUEST, "값이 누락되었거나 잘못된 값이 있습니다.");
        }

        // 이미 Todo가 등록되어 있는지 확인
        if (todosService.existsByUserId(userId)) {
            return Result.of(HttpStatus.CONFLICT, "이미 목표가 등록되어 있습니다.");
        }

        // Todos 등록 로직 호출
        todosService.createTodos(createDto, userId);
        return Result.of(HttpStatus.OK, "정상적으로 목표가 설정되었습니다.");
    }

    @GetMapping
    public ResponseEntity<?> getTodos() {
        String userId = userService.getSessionUserId();
        if (userId == null) {
            return Result.of(HttpStatus.FORBIDDEN, "로그인 후 이용하시기 바랍니다.");
        }

        try {
            TodosResponseDto responseDto = todosService.getTodosByUserId(userId);
            return Result.of(HttpStatus.OK, responseDto); // 여기에서 TodosResponseDto 객체를 직접 반환
        } catch (RuntimeException e) {
            return Result.of(HttpStatus.BAD_REQUEST, e.getMessage()); // 에러 메시지도 여전히 처리 가능
        }
    }
}
