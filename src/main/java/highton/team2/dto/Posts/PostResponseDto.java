package highton.team2.dto.Posts;

import highton.team2.dto.Comment.CommentResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PostResponseDto {
    private String userId;
    private Long postId;
    private String title;
    private String content;
    private List<CommentResponseDto> comments;
}
