package shop.project.mall.config.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import shop.project.mall.auth.LoginUser;
import shop.project.mall.config.jwt.JwtProcess;
import shop.project.mall.domain.constant.UserRole;
import shop.project.mall.domain.user.User;
import shop.project.mall.dto.request.user.LoginReqDto;
import shop.project.mall.dummy.DummyObject;
import shop.project.mall.repository.user.UserRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class JwtAuthorizationFilterTest extends DummyObject {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private UserRepository userRepository;


    @BeforeEach
    public void setUp() {
        userRepository.save(newUser("gus5162@naver.com","현우",UserRole.ADMIN));
        userRepository.save(newUser("gus5163@naver.com","현우",UserRole.USER));
    }

    @Test
    public void 권한_성공_테스트() throws Exception {

        // Given
        User user = User.builder().id(1L).auth(UserRole.USER).build();

        // When
        LoginUser loginUser = new LoginUser(user);
        JwtProcess.create(loginUser);
        ResultActions resultActions = mockMvc.perform(get("/api/s/hello/test"));

        // Then
        resultActions.andExpect(status().isNotFound());
    }

    @Test
    public void 권한_실패_테스트() throws Exception {

        // Given

        // When
        ResultActions resultActions = mockMvc.perform(get("/api/user/hello/test"));

        // Then
        resultActions.andExpect(status().isUnauthorized());
    }

    @Test
    public void 권한_어드민_성공_테스트() throws Exception {
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


        ResultActions perform = mockMvc.perform(get("/api/admin/ex").cookie(cookies));

        // Then
        perform.andExpect(status().isNotFound());
    }

    @Test
    public void 권한_어드민_실패_테스트() throws Exception {
        // Given

        LoginReqDto loginReqDto = new LoginReqDto();
        loginReqDto.setEmail("gus5163@naver.com");
        loginReqDto.setPassword("1234");
        loginReqDto.setChk("true");
        String requestBody = om.writeValueAsString(loginReqDto);
        // When
        ResultActions resultActions = mockMvc.perform(post("/api/login").content(requestBody).contentType(MediaType.APPLICATION_JSON));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        Cookie[] cookies = resultActions.andReturn().getResponse().getCookies();


        ResultActions perform = mockMvc.perform(get("/api/admin/ex").cookie(cookies));

        // Then
        perform.andExpect(status().isUnauthorized());
    }

}
