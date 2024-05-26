package com.smart_traffic.services;

import com.smart_traffic.adapters.Adapter;
import com.smart_traffic.config.security.TokenService;
import com.smart_traffic.dtos.user.request.UserLoginRequest;
import com.smart_traffic.dtos.user.request.UserRequest;
import com.smart_traffic.dtos.user.response.UserLoginResponse;
import com.smart_traffic.dtos.user.response.UserResponse;
import com.smart_traffic.exceptions.commons.NotFoundException;
import com.smart_traffic.exceptions.commons.ResourceCannotCreateException;
import com.smart_traffic.exceptions.commons.TokenException;
import com.smart_traffic.models.UserModel;
import com.smart_traffic.repositories.user.UserRepository;
import com.smart_traffic.repositories.user.UserRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final Adapter adapter;
    private final PasswordEncoder encoder;
    private final TokenService tokenService;
    private final UserRepository repository;
    private final UserRepositoryImpl userRepository;


    public UserResponse createUser(UserRequest userRequest){
        Optional<UserModel> findClientEmail = userRepository.findClientByEmail(userRequest.getUserEmail());
        if(findClientEmail.isEmpty()){
            UserModel user = adapter.map(userRequest, UserModel.class);
            user.setUserPassword(encoder.encode(user.getPassword()));
            repository.save(user);
            return adapter.map(user, UserResponse.class);
        }
        throw ResourceCannotCreateException.createResourceCannotCreateException("Email já existe");
    }

    public UserLoginResponse loginUser(UserLoginRequest userLoginRequest){
        UserModel user = userRepository.findClientByEmail(userLoginRequest.getUserEmail())
                .orElseThrow(() -> NotFoundException.createNotFoundException("Email não encontrado"));
        boolean senhasIguais = encoder.matches(userLoginRequest.getUserPassword(), user.getPassword());
        if (senhasIguais) {
            UserLoginResponse responseDTO = new UserLoginResponse();
            responseDTO.setLoginToken(tokenService.gerandoToken(user));
            return responseDTO;
        } else {
            throw TokenException.createTokenException("Credenciais inválidas");
        }
    }
}
