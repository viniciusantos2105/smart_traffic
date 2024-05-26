package com.smart_traffic.dtos.user.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginRequest {

    @Email(message = "Email invalido")
    private String userEmail;
    @NotNull(message = "Senha invalida")
    private String userPassword;
}
