package shop.project.mall.controller.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;
import shop.project.mall.domain.constant.UserRole;
import shop.project.mall.dto.request.user.JoinUserReqDto;
import shop.project.mall.dto.response.member.JoinUserResDto;
import shop.project.mall.dummy.DummyObject;
import shop.project.mall.repository.user.UserRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class UserControllerTest extends DummyObject {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper om;
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        dateClean();
        dateSetting();
    }

    @Test
    void 회원가입_성공_테스트() throws Exception {
        JoinUserReqDto joinUserReqDto = new JoinUserReqDto();
        joinUserReqDto.setEmail("gus5163");
        joinUserReqDto.setUsername("aaa");
        joinUserReqDto.setPassword("1234");
        joinUserReqDto.setNickname("aaaa");

        String requestBody = om.writeValueAsString(joinUserReqDto);
        ResultActions resultActions = mockMvc.perform(post("/api/join").content(requestBody).contentType(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isCreated());
    }

//    @Test
//    void 회원가입_실패
    private void dateSetting() {
        userRepository.save(newUser("gus5162@naver.com","현우", UserRole.USER));
    }
    private void dateClean() {
        userRepository.deleteAll();
    }

    @Test
    void 회원가입_실패_테스트() throws Exception {
        JoinUserReqDto joinUserReqDto = new JoinUserReqDto();
        joinUserReqDto.setEmail("gus5162@naver.com");
        joinUserReqDto.setUsername("aaa");
        joinUserReqDto.setPassword("1234");
        joinUserReqDto.setNickname("aaaa");

        String requestBody = om.writeValueAsString(joinUserReqDto);
        ResultActions resultActions = mockMvc.perform(post("/api/join").content(requestBody).contentType(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isBadRequest());
    }

}