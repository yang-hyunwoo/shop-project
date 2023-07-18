package shop.project.mall.config.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;
import shop.project.mall.domain.constant.UserRole;
import shop.project.mall.domain.user.User;
import shop.project.mall.dto.request.user.LoginReqDto;
import shop.project.mall.dummy.DummyObject;
import shop.project.mall.repository.user.UserRepository;
import shop.project.mall.util.encoder.Aes256Util;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@ActiveProfiles("test") //application-test.yml을 사용하겠다.
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment =  SpringBootTest.WebEnvironment.MOCK)
public class JwtAuthenticationFilterTest extends DummyObject {

    @Autowired
    private ObjectMapper om;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        userRepository.save(newUser("gus5162@naver.com","현우", UserRole.USER));
    }

    @Test
    public void 로그인_성공_테스트() throws Exception {
        // Given
        LoginReqDto loginReqDto = new LoginReqDto();
        loginReqDto.setEmail("gus5162@naver.com");
        loginReqDto.setPassword("1234");
        loginReqDto.setChk("true");
        String requestBody = om.writeValueAsString(loginReqDto);
        // When
        ResultActions resultActions = mockMvc.perform(post("/api/login").content(requestBody).contentType(MediaType.APPLICATION_JSON));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        Cookie[] cookies = resultActions.andReturn().getResponse().getCookies();
        String cookieValue = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("PA_T")) {
                    cookieValue = cookie.getValue();
                }
            }
        }
        // Then
        resultActions.andExpect(status().isOk());
        assertNotNull(cookieValue);

    }

    @Test
    public void 로그인_실패_테스트() throws Exception {
        LoginReqDto loginReqDto = new LoginReqDto();
        loginReqDto.setEmail("gus5162@naver.com");
        loginReqDto.setPassword("12345");
        String requestBody = om.writeValueAsString(loginReqDto);
        System.out.println("requestBody = " + requestBody);
        // When
        ResultActions resultActions = mockMvc.perform(post("/api/login").content(requestBody).contentType(MediaType.APPLICATION_JSON));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        Cookie[] cookies = resultActions.andReturn().getResponse().getCookies();
        String cookieValue = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("PA_T")) {
                    cookieValue = cookie.getValue();
                }
            }
        }
        resultActions.andExpect(status().isConflict());
    }
}
