package highton.team2.controller;

import highton.team2.dto.Posts.PostCreateDto;
import highton.team2.service.PostsService;
import highton.team2.util.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostsController {

    private final PostsService postsService;

    @PostMapping("/create")
    public ResponseEntity<?> createPost(@RequestBody PostCreateDto createDto) {
        try {
            postsService.createPost(createDto);
            // 성공 응답
            return Result.of(HttpStatus.OK, "정상적으로 글이 등록되었습니다.");
        } catch (IllegalStateException e) {
            // 로그인되어 있지 않은 경우
            return Result.of(HttpStatus.FORBIDDEN, e.getMessage());
        } catch (Exception e) {
            // 잘못된 값 입력
            return Result.of(HttpStatus.BAD_REQUEST, "값이 누락되었거나 잘못된 값이 있습니다.");
        }
    }
}
