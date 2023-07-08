package shop.project.mall.dto.response.member;

import lombok.Getter;
import lombok.Setter;
import shop.project.mall.domain.user.User;

@Getter
@Setter
public class LoginResDto {

    private Long id;
    private String username;
    private String createdAt;

    public LoginResDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
//        this.createdAt = CustomDateUtil.toStringFormat(user.get());
    }
}
