package shop.project.mall.config.jwt.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import shop.project.mall.auth.LoginUser;
import shop.project.mall.config.jwt.JwtProcess;
import shop.project.mall.dto.request.user.LoginReqDto;
import shop.project.mall.dto.response.member.LoginResDto;
import shop.project.mall.exception.error.ErrorCode;
import shop.project.mall.property.AesProperty;
import shop.project.mall.property.JwtProperty;
import shop.project.mall.service.user.UserService;
import shop.project.mall.util.encoder.Aes256Util;
import shop.project.mall.util.response.CustomResponseUtil;

import java.io.IOException;

import static shop.project.mall.config.jwt.JwtProcess.CreateCookie;
import static shop.project.mall.config.jwt.JwtProcess.CreateCookieJwt;


@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    private final boolean localCookie = false; //true : 로컬  false : 쿠키

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, UserService userService ) {
        super(authenticationManager);
        setFilterProcessesUrl("/api/login");
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            ObjectMapper om = new ObjectMapper();
            LoginReqDto loginReqDto = om.readValue(request.getInputStream(), LoginReqDto.class);

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginReqDto.getEmail(), loginReqDto.getPassword());

            return authenticationManager.authenticate(authenticationToken);
        } catch (Exception e) {
            throw new InternalAuthenticationServiceException(e.toString(),e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        LoginUser loginUser = (LoginUser) authResult.getPrincipal();
        String accessToken = JwtProcess.create(loginUser);
        String refreshToken = JwtProcess.refresh(loginUser);
        ObjectMapper om = new ObjectMapper();
        LoginReqDto loginReqDto = om.readValue(request.getInputStream(), LoginReqDto.class);

        /**
         * 헤더로 설정 or 쿠키로 설정
         */
        LoginResDto loginRespDto = new LoginResDto(loginUser.getUser());
        userService.userLgnFailInit(loginUser.getUser().getId());
        Aes256Util aes256 = new Aes256Util();
        String encrypt = aes256.encrypt(AesProperty.getAesBody(), loginReqDto.getChk());
        if(localCookie) {
            response.addHeader(JwtProperty.getHeader(), accessToken);
            response.addHeader("REFRESH_TOKEN", refreshToken);
            response.addHeader("PA_AUT", encrypt);
        } else {
            //쿠키 시간은 동일하게 맞춤
            response.addHeader("Set-cookie", CreateCookieJwt(accessToken, "PA_T").toString());
            response.addHeader("Set-cookie", CreateCookieJwt(refreshToken, "PR_T").toString());
            response.addHeader("Set-cookie", CreateCookie(encrypt, "PA_AUT").toString());
        }
        CustomResponseUtil.success(response, loginRespDto,"로그인 성공");
    }

    //로그인 실패
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        ErrorCode errorCode = unsuccessException(request, failed.getCause());
        CustomResponseUtil.fail(response, errorCode.getMessage(), errorCode.getStatus());
    }

    //request.getParameter("username") 조회할 거로 수정
    public ErrorCode unsuccessException(HttpServletRequest request, Throwable failed) throws IOException {
        ErrorCode errorCode;

        if (failed instanceof BadCredentialsException) {
            errorCode = ErrorCode.USER_EMAIL_PW_INVALIED;
            ObjectMapper om = new ObjectMapper();
            userService.userLgnFailCnt(om.readValue(request.getInputStream(), LoginReqDto.class).getEmail());//실패 횟수 증가
        } else if (failed instanceof InternalAuthenticationServiceException) {
            errorCode = ErrorCode.USER_EMAIL_PW_INVALIED;
        } else if (failed instanceof LockedException) {
            errorCode = ErrorCode.PASSWORD_WRONG;
        } else if (failed instanceof AuthenticationCredentialsNotFoundException) {
            errorCode = ErrorCode.USER_EMAIL_PW_INVALIED;
        } else if (failed instanceof DisabledException) {
            errorCode = ErrorCode.DISABLED_MEMBER;
        } else if (failed instanceof AccountExpiredException) {
            errorCode = ErrorCode.DORMANT_ACCOUNT;
        } else if (failed instanceof CredentialsExpiredException) {
            errorCode = ErrorCode.PASSWORD_DATE_OVER;
        } else {
            errorCode = ErrorCode.ANOTHER_ERROR;
        }
        return errorCode;
    }
}
