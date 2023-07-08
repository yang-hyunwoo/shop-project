package shop.project.mall.auth;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import shop.project.mall.domain.user.User;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;


@Getter
public class LoginUser implements UserDetails , OAuth2User {

    private final User user;
    private Map<String , Object> attributes;

    //일반 로그인
    public LoginUser(User user) {
        this.user = user;
    }

    //OAuth 로그인
    public LoginUser(User user, Map<String, Object> attributes) {
        this.user = user;
        this.attributes = attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(() -> "ROLE_" + user.getAuth());
        return authorities;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    /**
     * 휴면 계정 or 컬럼 생성(true , false) 해도 됨
     * 현재 일자 기준
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return ChronoUnit.DAYS.between(user.getLastAccessDate().toLocalDate(),LocalDate.now()) <=365;
    }

    /**
     * 비밀번호 오류 5회 이상
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return user.getLgnFlrCnt() <=4;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return ChronoUnit.DAYS.between(user.getPwChgDate().toLocalDate(),LocalDate.now()) <=90;
    }

    /**
     * 탈퇴여부
     * @return
     */
    @Override
    public boolean isEnabled() {
        return user.isUseYn();
    }

    @Override
    public String getName() {
        return null;
    }
}
