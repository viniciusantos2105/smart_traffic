package com.smart_traffic.exceptions.commons;

public class ResourceCannotCreateException extends RuntimeException{

    public static final String DEFAULT_MESSAGE = "Recurso não pode ser criado";

    public static final int DEFAULT_STATUS = 400;

    public ResourceCannotCreateException(String resourceName){
        super(resourceName);
    }

    public static ResourceCannotCreateException createResourceCannotCreateException(String resourceName){
        return new ResourceCannotCreateException(resourceName);
    }

    public static ResourceCannotCreateException createResourceCannotCreateException(){
        return new ResourceCannotCreateException(DEFAULT_MESSAGE);
    }
}
