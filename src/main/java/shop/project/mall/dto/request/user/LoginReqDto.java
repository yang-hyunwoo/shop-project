package shop.project.mall.dto.request.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginReqDto {

    private String email;

    private String password;

    private String chk;
}
