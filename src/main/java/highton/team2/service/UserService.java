package highton.team2.service;

import highton.team2.dto.User.UserCreateDto;
import highton.team2.dto.User.UserLoginDto;
import highton.team2.entity.User;
import highton.team2.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final HttpSession session;

    public String createUser(UserCreateDto userCreateDto) {
        User user = new User(userCreateDto.getUserId(), userCreateDto.getPassword(), userCreateDto.getName(), userCreateDto.getAge(), userCreateDto.getSchool(), userCreateDto.getLicense());
        userRepository.save(user);
        return "정상적으로 회원가입 되었습니다.";
    }

    public User findUserByUserId(String userId) {
        try {
            Optional<User> byId = userRepository.findById(userId);
            return byId.get();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean validateUserLogin(UserLoginDto loginDto) {
        return userRepository.findByUserIdAndPassword(loginDto.getUserId(), loginDto.getPassword()).isPresent();
    }

    public String getSessionUserId() {
        return (String) session.getAttribute("userId"); // 세션에서 사용자 아이디 가져오기
    }

}
