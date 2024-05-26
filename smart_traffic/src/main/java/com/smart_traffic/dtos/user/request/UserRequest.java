package com.smart_traffic.dtos.user.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    @Email(message = "Email invalido")
    @NotEmpty(message = "Email não pode ser vazio")
    private String userEmail;
    @NotEmpty(message = "Senha invalida")
    private String userPassword;

}
