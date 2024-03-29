package shop.project.mall.config.jwt;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import shop.project.mall.auth.LoginUser;
import shop.project.mall.domain.constant.UserRole;
import shop.project.mall.domain.user.User;
import shop.project.mall.property.AesProperty;
import shop.project.mall.property.JwtProperty;
import shop.project.mall.util.encoder.Aes256Util;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

@RequiredArgsConstructor
public class JwtProcess {

    //토큰 생성
    public static String create(LoginUser loginUser) {
        Aes256Util aes256 = new Aes256Util();
        String jwtToken = JWT.create()
                .withSubject("shop-project")
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperty.getExpirationTime()))
                .withClaim("id", aes256.encrypt(AesProperty.getAesBody(),loginUser.getUser().getId().toString()))
                .withClaim("role", aes256.encrypt(AesProperty.getAesBody(),loginUser.getUser().getAuth().name()))
                .sign(Algorithm.HMAC512(returnByte(JwtProperty.getSecretKey())));
        return JwtProperty.getTokenPrefix() + aes256.encrypt(AesProperty.getAesCreate(), jwtToken);
    }

    //refresh 토큰 생성
    public static String refresh(LoginUser loginUser) {
        Aes256Util aes256 = new Aes256Util();
        String jwtToken = JWT.create()
                .withSubject("shop-project")
                .withExpiresAt(new Date(System.currentTimeMillis() +JwtProperty.getExpirationTime()* 20L))
                .withClaim("id", aes256.encrypt(AesProperty.getAesBody(),loginUser.getUser().getId().toString()))
                .withClaim("refreshToken", aes256.encrypt(AesProperty.getAesBody(),UUID.randomUUID().toString()))
                .sign(Algorithm.HMAC512(returnByte(JwtProperty.getSecretKey())));
        return JwtProperty.getTokenPrefix() +aes256.encrypt(AesProperty.getAesRefresh(), jwtToken);
    }

    //토큰 검증 (return 되는 LoginUser 객체를 강제로 시큐리티 세션에 직접 주입)
    public static LoginUser verify(String token)  {
        Aes256Util aes256 = new Aes256Util();
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(returnByte(JwtProperty.getSecretKey())))
                .build()
                .verify(aes256.decrypt(AesProperty.getAesCreate(), token));
        Long id = Long.parseLong(aes256.decrypt(AesProperty.getAesBody(),decodedJWT.getClaim("id").asString()));
        String role = aes256.decrypt(AesProperty.getAesBody(),decodedJWT.getClaim("role").asString());
        User user = User.builder().id(id).auth(UserRole.valueOf(role)).build();
        return new LoginUser(user);
    }

    public static Long verifyRefresh(String token) {
        Aes256Util aes256 = new Aes256Util();
        String decrypt = aes256.decrypt(AesProperty.getAesRefresh(), token);
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(returnByte(JwtProperty.getSecretKey()))).build().verify(decrypt);
        return Long.parseLong(aes256.decrypt(AesProperty.getAesBody(), decodedJWT.getClaim("id").asString()));
    }

    public static boolean verifyExpired(String token) {
        Aes256Util aes256 = new Aes256Util();
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(returnByte(JwtProperty.getSecretKey())))
                .build()
                .verify(aes256.decrypt(AesProperty.getAesRefresh(), token));
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime refreshExpired = decodedJWT.getExpiresAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        //리프래시 토큰 만료일이 하루 남았을 경우 재생성하기 위함
        return ChronoUnit.DAYS.between(now, refreshExpired) <= 1 && ChronoUnit.DAYS.between(now, refreshExpired) >= 0;
    }

    public static ResponseCookie createCookieJwt(String accessToken , String cookieName) {
        return ResponseCookie.from(cookieName, accessToken.split(" ")[1].trim())
                .maxAge(7 * 24 * 60 * 60)
//                    .httpOnly(true)
//                    .secure(true)
                .path("/")
                .build();
    }

    public static ResponseCookie createCookie(String cookieValue , String cookieName) {
        return ResponseCookie.from(cookieName, cookieValue)
                .maxAge(7 * 24 * 60 * 60)
//                    .httpOnly(true)
//                    .secure(true)
                .path("/")
                .build();
    }

    public static byte[] returnByte(String secretKey) {
        return secretKey.getBytes(StandardCharsets.UTF_8);
    }
}
