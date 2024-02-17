package highton.team2.controller;

import highton.team2.dto.Comment.CommentCreateDto;
import highton.team2.entity.Comment;
import highton.team2.service.CommentService;
import highton.team2.util.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/create")
    public ResponseEntity<?> createComment(@RequestBody CommentCreateDto dto) {
        try {
            Comment comment = commentService.createComment(dto);
            return Result.of(HttpStatus.OK, "Comment created successfully");
        } catch (Exception e) {
            return Result.of(HttpStatus.BAD_REQUEST, "Error creating comment: " + e.getMessage());
        }
    }
}
