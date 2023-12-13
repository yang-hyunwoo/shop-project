package shop.project.mall.dto.request.user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import shop.project.mall.domain.common.AttachFile;

@Getter
@Setter
@ToString
public class UserDtlModifyReqDto {

    private AttachFile attachFile;

    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z가-힣]{1,20}$" , message = "영문/한글 1~20자 이내로 작성해 주세요.")
    private String nickName;

    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z0-9]{2,20}$", message = "영문/숫자 2~20자 이내로 작성해 주세요.")
    private String username;

    @NotEmpty
    private String phoneNumber;

}
