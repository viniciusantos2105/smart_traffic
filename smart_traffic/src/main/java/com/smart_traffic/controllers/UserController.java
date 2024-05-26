    package com.smart_traffic.controllers;

import com.smart_traffic.dtos.user.request.UserLoginRequest;
import com.smart_traffic.dtos.user.request.UserRequest;
import com.smart_traffic.dtos.user.response.UserLoginResponse;
import com.smart_traffic.dtos.user.response.UserResponse;
import com.smart_traffic.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {


    private final UserService service;


    @Operation(summary = "Cadastro de usuario", description = "Este endpoint post, consiste no criação de um usuario", tags = {"User"})
    @PostMapping()
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest){
        UserResponse responseDTO = service.createUser(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @Operation(summary = "Login de usuario", description = "Este endpoint post, consiste no login de um usuario", tags = {"User"})
    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> loginUser(@Valid @RequestBody UserLoginRequest userLoginRequest){
        UserLoginResponse responseDTO = service.loginUser(userLoginRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
}
