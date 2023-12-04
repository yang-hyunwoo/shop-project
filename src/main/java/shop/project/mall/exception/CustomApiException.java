package shop.project.mall.exception;

import lombok.Getter;
import shop.project.mall.exception.error.ErrorCode;

@Getter
public class CustomApiException extends RuntimeException{


    public CustomApiException(String message) {
        super(message);
    }

}
