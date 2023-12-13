package shop.project.mall.dto.request.user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginReqDto {

    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$",message ="이메일 형식으로 작성해 주세요." )
    private String email;

    @NotEmpty
    @Pattern(regexp = "^(?=.*\\d)(?=.*[!@#$%^&*])[A-Za-z\\d!@#$%^&*]{8,20}$",message ="숫자,특수문자,영문을 조합하여 8~20자가 필요합니다." )
    private String password;


    private String chk;
}
