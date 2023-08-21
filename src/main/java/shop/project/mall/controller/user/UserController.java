package shop.project.mall.controller.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import shop.project.mall.dto.request.user.JoinUserReqDto;
import shop.project.mall.dto.response.member.JoinUserResDto;
import shop.project.mall.service.user.UserService;
import shop.project.mall.util.response.Response;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "Template", description = "템플릿 API Document")
public class UserController {

    private final UserService userService;

    @Operation(summary = "회원가입", description = "회원가입을 요청합니다.", tags = { "UserController" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "successful operation"),
            @ApiResponse(responseCode = "400", description = "bad request operation", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PostMapping("/join")
    public ResponseEntity<Response<JoinUserResDto>> join(@Valid @RequestBody JoinUserReqDto joinReqDto , BindingResult bindingResult) {
        JoinUserResDto join = userService.join(joinReqDto);
        return new ResponseEntity<>(Response.successNew(join), HttpStatus.CREATED);
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
