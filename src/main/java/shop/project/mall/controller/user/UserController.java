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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import shop.project.mall.auth.LoginUser;
import shop.project.mall.domain.log.Log;
import shop.project.mall.dto.request.user.JoinUserReqDto;
import shop.project.mall.dto.request.user.UserChangePwdReqDto;
import shop.project.mall.dto.request.user.UserDtlModifyReqDto;
import shop.project.mall.dto.response.member.JoinUserResDto;
import shop.project.mall.exception.CustomApiException;
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
    public ResponseEntity<Response<JoinUserResDto>> join(@Valid @RequestBody JoinUserReqDto joinReqDto)
    {
        JoinUserResDto join = userService.join(joinReqDto);
        return new ResponseEntity<>(Response.successNew(join), HttpStatus.CREATED);
    }

    @Operation(summary = "비밀번호 변경", description = "비밀번호 변경을 요청합니다.", tags = { "UserController" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "400", description = "bad request operation", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PutMapping("/change-password")
    public ResponseEntity<Response<Boolean>> changePassword(@Valid @RequestBody UserChangePwdReqDto userChangePwdRequest
                                                                 , @AuthenticationPrincipal LoginUser loginUser)
    {
        userService.changePassword(userChangePwdRequest , loginUser);
        return new ResponseEntity<>(Response.successUpdate(true), HttpStatus.OK);
    }

    @Operation(summary = "회원정보 변경", description = "회원정보 변경을 요청합니다.", tags = { "UserController" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "400", description = "bad request operation", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PutMapping("/user-modify")
    public ResponseEntity<Response<Boolean>> userModify(@Valid @RequestBody UserDtlModifyReqDto userDtlModifyReqDto
                                                             , @AuthenticationPrincipal LoginUser loginUser)
    {
        userService.userDtlModify(userDtlModifyReqDto, loginUser);
        return new ResponseEntity<>(Response.successUpdate(true), HttpStatus.OK);
    }



}
