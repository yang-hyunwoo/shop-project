package shop.project.mall.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import shop.project.mall.util.response.Response;

@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    @ExceptionHandler(CustomApiException.class)
    public ResponseEntity<?> apiException(CustomApiException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(Response.error("ERROR", HttpStatus.BAD_REQUEST.value(), e.getMessage()),HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(CustomValidationApiException.class)
    public ResponseEntity<?> validationApiException(CustomValidationApiException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(Response.error("VALIDATION_ERROR", HttpStatus.BAD_REQUEST.value(), e.getErrorMap()),HttpStatus.BAD_REQUEST);
    }
//
//    @ExceptionHandler(CustomForbiddenException.class)
//    public ResponseEntity<?> forbiddenException(CustomForbiddenException e) {
//        log.error(e.getMessage());
//        return new ResponseEntity<>(new ResponseDto<>(-1, e.getMessage(), null), HttpStatus.FORBIDDEN);
//
//    }


}
