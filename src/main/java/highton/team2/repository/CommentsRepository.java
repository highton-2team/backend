package highton.team2.repository;

import highton.team2.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostId(Long postId); // 게시글 ID로 댓글 조회
}
