package com.koreait.library.aop.Exception;

import com.koreait.library.web.dto.CMRespDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

    public ResponseEntity<?> validationError(CustomValidationException e){
        return ResponseEntity.badRequest().body(new CMRespDto<>("Validation Error", e.getErrorMap()));
    }
}
