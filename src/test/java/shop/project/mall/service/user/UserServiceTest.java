package shop.project.mall.service.user;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import shop.project.mall.auth.LoginUser;
import shop.project.mall.domain.constant.UserRole;
import shop.project.mall.domain.user.User;
import shop.project.mall.dto.request.user.JoinUserReqDto;
import shop.project.mall.dto.request.user.UserChangePwdReqDto;
import shop.project.mall.dto.response.member.JoinUserResDto;
import shop.project.mall.dummy.DummyObject;
import shop.project.mall.exception.CustomApiException;
import shop.project.mall.exception.error.ErrorCode;
import shop.project.mall.repository.user.UserRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest extends DummyObject {

    @InjectMocks
    private UserService userService;

    @Mock //가짜 객체
    private UserRepository userRepository;

    @Mock
    private EntityManager em;

    @Spy //진짜를 꺼내 InjectMocks에 넣어준다
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @BeforeEach
    public void setUp() {
        dateSetting();
    }

    private void dateSetting() {

    }

    @Test
    void 회원가입_성공_테스트() {
        JoinUserReqDto joinUserReqDto = new JoinUserReqDto();
        joinUserReqDto.setEmail("gus5162@naver.com");
        joinUserReqDto.setPassword("1111");
        joinUserReqDto.setNickname("test");

        when(userRepository.findByEmail(any())).thenReturn(Optional.empty());
        when(bCryptPasswordEncoder.encode(joinUserReqDto.getPassword())).thenReturn("encrypt_password");

        User hw = newMockUser(1L, "gus5162@naver.com", "현", UserRole.USER);
        when(userRepository.save(any())).thenReturn(hw);
        JoinUserResDto join = userService.join(joinUserReqDto);

        assertThat(join.getId()).isEqualTo(1L);
        assertThat(join.getEmail()).isEqualTo("gus5162@naver.com");
    }

    @Test
    void 회원가입_실패_테스트() {

        JoinUserReqDto joinUserReqDto = new JoinUserReqDto();
        joinUserReqDto.setEmail("gus5162@naver.com");
        joinUserReqDto.setPassword("1111");
        joinUserReqDto.setNickname("test");

        User hw = newMockUser(1L, "gus5162@naver.com", "현", UserRole.USER);
        when(userRepository.findByEmail(joinUserReqDto.getEmail())).thenReturn(Optional.of(hw));

        CustomApiException e = assertThrows(CustomApiException.class, () -> userService.join(joinUserReqDto));
        assertEquals(ErrorCode.DUPLICATED_EMAIL.getMessage() , e.getMessage());

    }

    @Test
    void 비밀번호_변경_시_성공() {

        User hw = newMockUser(1L, "gus5162@naver.com", "현", UserRole.USER);
        LoginUser loginUser = new LoginUser(hw);

        UserChangePwdReqDto userChangePwdReqDto = new UserChangePwdReqDto();
        userChangePwdReqDto.setPwd("dnfntm122##");
        userChangePwdReqDto.setPwdConfirm("dnfntm122##");
        userService.changePassword(userChangePwdReqDto, loginUser);

    }


    @Test
    void 비밀번호_변경_시_일치하지_않을_경우() {
        User hw = newMockUser(1L, "gus5162@naver.com", "현", UserRole.USER);
        LoginUser loginUser = new LoginUser(hw);
        UserChangePwdReqDto userChangePwdReqDto = new UserChangePwdReqDto();
        userChangePwdReqDto.setPwd("dnfntm12233##");
        userChangePwdReqDto.setPwdConfirm("dnfntm122##");

        CustomApiException e = assertThrows(CustomApiException.class, () -> userService.changePassword(userChangePwdReqDto, loginUser));
        assertEquals(ErrorCode.PASSWORD_DO_NOT_MATCH.getMessage() , e.getMessage());

    }


}
