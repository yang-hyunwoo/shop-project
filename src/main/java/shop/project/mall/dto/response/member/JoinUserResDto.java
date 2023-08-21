package shop.project.mall.dto.response.member;

import lombok.Data;
import shop.project.mall.domain.user.User;

@Data
public class JoinUserResDto {

    private Long id;

    private String username;

    private String nickname;

    private String email;

    public JoinUserResDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.nickname = user.getNickname();
        this.email = user.getEmail();
    }
}
