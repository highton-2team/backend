package highton.team2.service;

import highton.team2.dto.Posts.PostCreateDto;
import highton.team2.entity.Posts;
import highton.team2.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor // Lombok을 사용하여 생성자 자동 생성
public class PostsService {

    private final PostsRepository postsRepository;
    private final UserService userService; // final로 선언된 필드에 대한 생성자를 자동 생성

    @Transactional
    public String createPost(PostCreateDto createDto) {
        String currentUserId = userService.getSessionUserId(); // 현재 로그인한 사용자 ID 가져오기
        if (currentUserId == null) {
            throw new IllegalStateException("로그인 후 이용하시기 바랍니다.");
        }

        Posts post = new Posts();
        post.setUserId(currentUserId);
        post.setTitle(createDto.getTitle());
        post.setContent(createDto.getContent());
        postsRepository.save(post);

        return "정상적으로 글이 등록되었습니다.";
    }
}
