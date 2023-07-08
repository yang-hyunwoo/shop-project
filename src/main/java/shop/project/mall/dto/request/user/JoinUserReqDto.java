package shop.project.mall.dto.request.user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;
import shop.project.mall.domain.constant.UserRole;
import shop.project.mall.domain.user.User;

import java.time.LocalDateTime;

@Getter
@Setter
public class JoinUserReqDto {

    @Pattern(regexp = "^[a-zA-Z0-9]{2,20}$", message = "영문/숫자 2~20자 이내로 작성해 주세요.")
    @NotEmpty
    private String username;
    @NotEmpty
    @Size(min = 4, max = 20)
    private String password;
    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z0-9]{2,6}@[a-zA-Z0-9]{2,6}\\.[a-zA-Z]{2,3}$",message ="이메일 형식으로 작성해 주세요." )
    private String email;

    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z가-힣]{1,20}$" , message = "영문/한글 1~20자 이내로 작성해 주세요.")
    private String nickname;

    public User toEntity(PasswordEncoder passwordEncoder) {
        return User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .email(email)
                .nickname(nickname)
                .auth(UserRole.USER)
                .lgnFlrCnt(0)
                .useYn(true)
                .pwChgDate(LocalDateTime.now())
                .build();
    }


    @Override
    public String toString() {
        return "JoinUserReqDto{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
