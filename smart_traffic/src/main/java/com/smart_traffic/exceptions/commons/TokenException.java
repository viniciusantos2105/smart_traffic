package com.smart_traffic.exceptions.commons;

public class TokenException extends RuntimeException{

    public static final String DEFAULT_MESSAGE = "Token inválido";

    public static final int DEFAULT_STATUS = 401;

    public TokenException(String token){
        super(token);
    }

    public static TokenException createTokenException(String token){
        return new TokenException(token);
    }

    public static TokenException createTokenException(){
        return new TokenException(DEFAULT_MESSAGE);
    }
}
