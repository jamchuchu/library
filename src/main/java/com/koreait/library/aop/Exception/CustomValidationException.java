package com.koreait.library.aop.Exception;

import lombok.Data;

import java.util.Map;

@Data
public class CustomValidationException extends RuntimeException{
    private Map<String, String> errorMap;
}
