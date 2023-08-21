package shop.project.mall.dummy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import shop.project.mall.domain.constant.UserRole;
import shop.project.mall.domain.user.User;

import java.time.LocalDateTime;

public class DummyObject {

    protected User newUser(String email , String username,UserRole userRole) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encPassword = passwordEncoder.encode("1234");

        return User.builder()
                .email(email)
                .username(username)
                .nickname(username)
                .password(encPassword)
                .pwChgDate(LocalDateTime.now())
                .auth(userRole)
                .useYn(true)
                .build();
    }

    protected User newMockUser(Long id ,String email , String username,UserRole userRole) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encPassword = passwordEncoder.encode("1234");
        return User.builder()
                .id(id)
                .email(email)
                .username(username)
                .nickname(username)
                .password(encPassword)
                .pwChgDate(LocalDateTime.now())
                .auth(userRole)
                .useYn(true)
                .build();
    }
}
