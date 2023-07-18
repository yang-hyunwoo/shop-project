package shop.project.mall.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@AutoConfigureMockMvc //Mock(가짜) 환경에 MockMvc가 등록됨
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK) //가짜 환영에서 함
public class SecurityConfigTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void 인증_테스트() throws Exception {
        // Given
        // When
        ResultActions perform = mvc.perform(get("/api/admin/hello"));
        String responseBody = perform
                .andReturn()
                .getResponse()
                .getContentAsString();
        int status = perform.andReturn().getResponse().getStatus();

        // Then
        assertThat(status).isEqualTo(401);
    }

    @Test
    public void 권한_테스트() throws Exception {
        // Given

        // When
        ResultActions perform = mvc.perform(get("/api/admin/hello"));
        String responseBody = perform
                .andReturn()
                .getResponse()
                .getContentAsString();
        int status = perform.andReturn().getResponse().getStatus();

        assertThat(status).isEqualTo(401);
        // Then

    }
}
