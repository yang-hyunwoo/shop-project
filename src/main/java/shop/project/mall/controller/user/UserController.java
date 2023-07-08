package shop.project.mall.controller.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import shop.project.mall.dto.request.user.JoinUserReqDto;
import shop.project.mall.dto.response.member.JoinUserResDto;
import shop.project.mall.service.user.UserService;
import shop.project.mall.util.response.Response;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "Template", description = "템플릿 API Document")
public class UserController {

    private final UserService userService;

    @Operation(summary = "TEST 요청", description = "TEST 삭제됩니다.", tags = { "MemberController" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "400", description = "bad request operation", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PostMapping("/join")
    public Response<JoinUserResDto> join(@RequestBody @Valid JoinUserReqDto joinReqDto , BindingResult bindingResult) {

        JoinUserResDto join = userService.join(joinReqDto);
        return Response.successRead(join);
    }

    @GetMapping("/all")
    public String aaa() {
        return "aaa";
    }

    @GetMapping("/all2")
    public String aaa2() {
        return "aaa2";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }
}
