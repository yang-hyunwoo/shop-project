package shop.project.mall.oauth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import shop.project.mall.auth.LoginUser;
import shop.project.mall.config.jwt.JwtProcess;
import shop.project.mall.property.AesProperty;
import shop.project.mall.util.encoder.Aes256Util;

import java.io.IOException;

import static shop.project.mall.config.jwt.JwtProcess.CreateCookie;
import static shop.project.mall.config.jwt.JwtProcess.CreateCookieJwt;


@Component
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    //JwtAuthenticationFilter와 동일
   @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        String targetUrl = determineTargetUrl(request, response, authentication);
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();

        String accessToken = JwtProcess.create(loginUser);
        String refreshToken = JwtProcess.refresh(loginUser);

        Aes256Util aes256 = new Aes256Util();
        String encrypt = aes256.encrypt(AesProperty.getAesBody(), "true");

        response.addHeader("Set-cookie", CreateCookieJwt(accessToken, "PA_T").toString());
        response.addHeader("Set-cookie", CreateCookieJwt(refreshToken, "PR_T").toString());
        response.addHeader("Set-cookie", CreateCookie(encrypt, "PA_AUT").toString());

        clearAuthenticationAttributes(request, response);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        if(loginUser.getUser().getProvider().equals("naver")) {
            return UriComponentsBuilder.fromUriString("http://localhost:4000/NaverLoginCallback")
                    .build().toUriString();
        } else {
            return UriComponentsBuilder.fromUriString("http://localhost:4000")
                    .build().toUriString();
        }
    }

    protected void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {
        super.clearAuthenticationAttributes(request);
    }

}
