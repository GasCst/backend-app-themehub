package com.themehub.errorhandler;

public class EntityUnauthorizedException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public EntityUnauthorizedException() {
    }

    public EntityUnauthorizedException(String message) {
        super(message);
    }

    public EntityUnauthorizedException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityUnauthorizedException(Throwable cause) {
        super(cause);
    }

    public EntityUnauthorizedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
