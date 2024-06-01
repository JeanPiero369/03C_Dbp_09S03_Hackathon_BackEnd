package com.example.hackathon.Auth.application;

import com.example.hackathon.Auth.domain.AuthService;
import com.example.hackathon.Auth.dto.RpJwtAuthtentication;
import com.example.hackathon.Auth.dto.RqUserLoginDto;
import com.example.hackathon.Auth.dto.RqUserRegisterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<RpJwtAuthtentication> entrar(@RequestBody RqUserLoginDto rqUserDto) {
        RpJwtAuthtentication token=authService.login(rqUserDto);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public ResponseEntity<RpJwtAuthtentication> registrar(@RequestBody RqUserRegisterDto usuario) {
        RpJwtAuthtentication token=authService.register(usuario);
        return ResponseEntity.ok(token);
    }

}
