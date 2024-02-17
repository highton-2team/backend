package highton.team2.service;

import highton.team2.dto.Comment.CommentCreateDto;
import highton.team2.entity.Comment;
import highton.team2.repository.CommentsRepository;
import highton.team2.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentsRepository commentRepository;
    private final PostsRepository postsRepository;
    private final UserService userService;

    @Transactional
    public Comment createComment(CommentCreateDto dto) {
        String userId = userService.getSessionUserId();
        var post = postsRepository.findById(dto.getPostId())
                .orElseThrow(() -> new IllegalArgumentException("Post not found with id: " + dto.getPostId()));

        Comment comment = new Comment();
        comment.setUserId(userId);
        comment.setContent(dto.getContent());
        comment.setPost(post);

        return commentRepository.save(comment);
    }
}
