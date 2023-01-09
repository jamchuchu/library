package com.koreait.library.web.controller.api;

import com.koreait.library.aop.annotation.ValidAspect;
import com.koreait.library.web.dto.CMRespDto;
import com.koreait.library.web.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/account")
public class AccountApi {

    @ValidAspect
    @PostMapping("/register")
        public ResponseEntity<?> register(@RequestBody @Valid UserDto userDto, BindingResult bindingResult){
        return ResponseEntity.created(null).body(new CMRespDto<>("create a new user", null));
    }
}
