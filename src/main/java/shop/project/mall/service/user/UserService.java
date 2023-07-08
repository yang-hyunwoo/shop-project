package shop.project.mall.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.project.mall.domain.user.User;
import shop.project.mall.dto.request.user.JoinUserReqDto;
import shop.project.mall.dto.response.member.JoinUserResDto;
import shop.project.mall.exception.CustomApiException;
import shop.project.mall.exception.error.ErrorCode;
import shop.project.mall.repository.user.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    public JoinUserResDto join(JoinUserReqDto userReqDto) {

        userRepository.findByEmail(userReqDto.getEmail()).ifPresent(user -> {
            throw new CustomApiException(ErrorCode.DUPLICATED_EMAIL.getMessage());
        });

        User user = userRepository.save(userReqDto.toEntity(passwordEncoder));
        return new JoinUserResDto(user);
    }

    public void userLgnFailCnt(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new CustomApiException(ErrorCode.USER_INVALIED.getMessage()));
        if(user.getLgnFlrCnt() <=4) {
            user.lgnFlrCntPlus();
        }
    }

    public void userLgnFailInit(Long id) {
        userRepository.findById(id).orElseThrow(() -> new CustomApiException(ErrorCode.USER_INVALIED.getMessage())).lgnFlrCntInit();
    }
}
