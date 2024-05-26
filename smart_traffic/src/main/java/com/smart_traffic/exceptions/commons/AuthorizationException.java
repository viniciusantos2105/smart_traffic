package com.smart_traffic.exceptions.commons;

public class AuthorizationException extends RuntimeException{

    private static final String DEFAULT_MESSAGE = " Autorização negada";
    public static final int DEFAULT_STATUS = 403;

    private AuthorizationException(String message) {
        super(message);
    }

    public static AuthorizationException createAuthorizationException(String message) {
        return new AuthorizationException(message);
    }
}
