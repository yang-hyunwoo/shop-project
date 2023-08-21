package shop.project.mall.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.project.mall.domain.user.User;
import shop.project.mall.exception.error.ErrorCode;
import shop.project.mall.repository.user.UserRepository;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class LoginService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new InternalAuthenticationServiceException(ErrorCode.USER_INVALIED.getMessage()));
        return new LoginUser(user);
    }
}
