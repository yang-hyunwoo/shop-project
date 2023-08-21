package shop.project.mall.exception;

import lombok.Getter;

import java.util.Map;

@Getter
public class CustomValidationApiException extends RuntimeException{


    private Map<String, String> errorMap;

    public CustomValidationApiException(String message, Map<String, String> errorMap) {
        super(message);
        this.errorMap = errorMap;
    }

}
