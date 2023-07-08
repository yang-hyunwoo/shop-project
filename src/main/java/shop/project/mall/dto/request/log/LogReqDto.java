package shop.project.mall.dto.request.log;

import lombok.Getter;
import lombok.Setter;
import shop.project.mall.auth.LoginUser;
import shop.project.mall.domain.log.Log;

@Getter
@Setter
public class LogReqDto {

    private String uuid;

    private boolean sucesStts;

    private String methodName;

    private String httpMethod;

    private String response;

    private String request;

    private String errorMsg;

    public static LogReqDto of(String uuid,
                     boolean sucesStts,
                     String methodName,
                     String httpMethod,
                     String response,
                     String request,
                     String errorMsg) {
        return new LogReqDto(uuid,
                sucesStts,
                methodName,
                httpMethod,
                response,
                request,
                errorMsg);
    }

    private LogReqDto(String uuid,
                      boolean sucesStts,
                      String methodName,
                      String httpMethod,
                      String response,
                      String request,
                      String errorMsg) {
        this.uuid = uuid;
        this.sucesStts = sucesStts;
        this.methodName = methodName;
        this.httpMethod = httpMethod;
        this.response = response;
        this.request = request;
        this.errorMsg = errorMsg;
    }

    public Log toEntity(LoginUser loginUser) {
        return Log.builder()
                .user(loginUser.getUser())
                .uuid(uuid)
                .sucesStts(sucesStts)
                .methodName(methodName)
                .httpMethod(httpMethod)
                .response(response)
                .request(request)
                .errorMsg(errorMsg)
                .build();
    }

}
