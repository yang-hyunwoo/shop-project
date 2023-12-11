package shop.project.mall.dto.request.user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserChangePwdReqDto {

    @NotEmpty
    @Pattern(regexp = "^(?=.*\\d)(?=.*[!@#$%^&*])[A-Za-z\\d!@#$%^&*]{8,20}$",message ="숫자,특수문자,영문을 조합하여 8~20자가 필요합니다." )
    private String pwd;

    @NotEmpty
    @Pattern(regexp = "^(?=.*\\d)(?=.*[!@#$%^&*])[A-Za-z\\d!@#$%^&*]{8,20}$",message ="숫자,특수문자,영문을 조합하여 8~20자가 필요합니다." )
    private String pwdConfirm;


}
