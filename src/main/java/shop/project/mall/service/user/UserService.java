package shop.project.mall.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.project.mall.auth.LoginUser;
import shop.project.mall.domain.user.User;
import shop.project.mall.dto.request.user.JoinUserReqDto;
import shop.project.mall.dto.request.user.UserChangePwdReqDto;
import shop.project.mall.dto.response.member.JoinUserResDto;
import shop.project.mall.exception.CustomApiException;
import shop.project.mall.exception.error.ErrorCode;
import shop.project.mall.repository.user.UserRepository;
import shop.project.mall.util.response.Response;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;


    /**
     * 회원 가입을 한다.
     * @param userReqDto
     * @return
     */
    public JoinUserResDto join(JoinUserReqDto userReqDto) {

        userRepository.findByEmail(userReqDto.getEmail()).ifPresent(user -> {
            throw new CustomApiException(ErrorCode.DUPLICATED_EMAIL.getMessage());
        });

        User user = userRepository.save(userReqDto.toEntity(passwordEncoder));
        return new JoinUserResDto(user);
    }

//    public Boolean changePassword(UserChangePwdReqDto userChangePwdReqDto, LoginUser loginUser) {
//
//    }

    /**
     * 사용자가 로그인시 비밀번호를 틀릴때 작동 한다.
     * @param email
     */
    public void userLgnFailCnt(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new CustomApiException(ErrorCode.USER_INVALIED.getMessage()));
        if(user.getLgnFlrCnt() <=4) {
            user.lgnFlrCntPlus();
        }
    }

    /**
     * 사용자의 비밀번호 오류 횟수를 초기화 한다.
     * @param id
     */
    public void userLgnFailInit(Long id) {
        userRepository.findById(id).orElseThrow(() -> new CustomApiException(ErrorCode.USER_INVALIED.getMessage())).lgnFlrCntInit();
    }

    /**
     * 사용자의 비밀번호를 변경 한다.
     */
    public void changePassword(UserChangePwdReqDto userChangePwdReqDto, LoginUser loginUser) {
        if(!userChangePwdReqDto.getPwd().equals(userChangePwdReqDto.getPwdConfirm())) {
            throw new CustomApiException(ErrorCode.PASSWORD_DO_NOT_MATCH.getMessage());
        }
        User user = userRepository.findById(loginUser.getUser().getId()).orElseThrow(() -> new CustomApiException(ErrorCode.USER_INVALIED.getMessage()));
        user.userChangePassword(passwordEncoder.encode(userChangePwdReqDto.getPwd()));
    }
}
