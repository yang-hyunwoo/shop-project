package shop.project.mall.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import shop.project.mall.config.jwt.filter.JwtAuthenticationFilter;
import shop.project.mall.config.jwt.filter.JwtAuthorizationFilter;
import shop.project.mall.oauth.OAuth2AuthenticationFailureHandler;
import shop.project.mall.oauth.OAuth2AuthenticationSuccessHandler;
import shop.project.mall.oauth.PrincipalOauth2UserService;
import shop.project.mall.repository.user.UserRepository;
import shop.project.mall.service.user.UserService;
import shop.project.mall.util.handler.CustomAccessDeniedHandler;
import shop.project.mall.util.handler.CustomAuthenticationEntryPoint;
import shop.project.mall.util.handler.CustomLogOutHandler;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final CorsConfig corsConfig;
    private final UserRepository userRepository;
    private final UserService userService;
    private final PrincipalOauth2UserService principalOauth2UserService;
    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
    private final OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        AuthenticationManager authenticationManager = authenticationManager(http.getSharedObject(AuthenticationConfiguration.class));

        return http.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(corsConfig.configurationSource()))
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .formLogin(AbstractHttpConfigurer::disable)
                .oauth2Login(oauth2Login -> oauth2Login.authorizationEndpoint(authorizationEndpoint-> authorizationEndpoint
                        .baseUri("/oauth2/authorization"))
                        .redirectionEndpoint(redirectionEndpoint-> redirectionEndpoint.baseUri("/login/oauth2/code/**"))
                        .userInfoEndpoint(userInfoEndpointConfig -> userInfoEndpointConfig.userService(principalOauth2UserService))
                        .successHandler(oAuth2AuthenticationSuccessHandler)
                        .failureHandler(oAuth2AuthenticationFailureHandler))
                .httpBasic(AbstractHttpConfigurer::disable)
                .logout(logout-> logout.logoutUrl("/api/logout").logoutSuccessHandler(new CustomLogOutHandler())
                        .deleteCookies("PA_T")
                        .deleteCookies("PR_T")
                        .deleteCookies("PA_AUT"))
                .addFilter(new JwtAuthenticationFilter(authenticationManager, userService))
                .addFilterBefore(new JwtAuthorizationFilter(authenticationManager, userRepository), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(exceptionHandling -> exceptionHandling.authenticationEntryPoint(new CustomAuthenticationEntryPoint()))
                .exceptionHandling(exceptionHandling -> exceptionHandling.accessDeniedHandler(new CustomAccessDeniedHandler()))
                .authorizeHttpRequests(authorizeHttpRequests ->
                        authorizeHttpRequests
                                .requestMatchers("/api/user/**")
                                .authenticated())
                .authorizeHttpRequests(authorizeHttpRequests ->
                        authorizeHttpRequests
                                .requestMatchers("/api/admin/**")
                                .hasRole("ADMIN"))
                .authorizeHttpRequests(authorizeHttpRequests ->
                        authorizeHttpRequests
                                .anyRequest()
                                .permitAll()).build();
    }

    //시큐리티 적용하지 않을 path
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/css/**", "/images/**", "/js/**");
    }
}
