package shop.project.mall.exception.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    DUPLICATED_EMAIL(HttpStatus.CONFLICT,"이미 사용중인 이메일 입니다."),
    USER_EMAIL_PW_INVALIED(HttpStatus.CONFLICT,"ID 및 비밀번호를 확인해 주세요."),
    PASSWORD_WRONG(HttpStatus.CONFLICT,"비밀번호 5회 오류로 인해 계정이 잠겼습니다."),
    DISABLED_MEMBER(HttpStatus.CONFLICT,"비활성화된 계정입니다."),
    PASSWORD_DATE_OVER(HttpStatus.CONFLICT,"비밀번호를 변경한지 3개월이 지났습니다."),
    DORMANT_ACCOUNT(HttpStatus.CONFLICT,"휴면 계정입니다."),
    ANOTHER_ERROR(HttpStatus.CONFLICT,"관리자에게 문의하세요."),
    USER_INVALIED(HttpStatus.RESET_CONTENT , "존재하지 않는 사용자입니다."),
    ENCRYPTION_ERROR(HttpStatus.CONFLICT, "암호화 오류 입니다."),
    DECODE_ERROR(HttpStatus.CONFLICT, "복호화 오류 입니다."),
    NO_AUTHORIZATION(HttpStatus.UNAUTHORIZED, "권한이 없습니다."),
    FIRST_LOGIN_ING(HttpStatus.CONFLICT, "로그인을 진행해 주세요."),
    PASSWORD_DO_NOT_MATCH(HttpStatus.CONFLICT , "비밀번호가 일치하지 않습니다."),
    ;

    private HttpStatus status;
    private String message;
}

