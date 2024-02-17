package highton.team2.dto.Comment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentCreateDto {
    private Long postId; // 댓글을 달 게시글의 ID
    private String content; // 댓글 내용
}
