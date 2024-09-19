package com.themehub.errorhandler;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EntityGenericServerException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String code;

    public EntityGenericServerException() {
        super();
    }

    public EntityGenericServerException(String message, String code) {
        super(message);
        this.code = code;
    }

    public EntityGenericServerException(String message, Throwable cause, boolean enableSuppression,
                                        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public EntityGenericServerException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityGenericServerException(String message) {
        super(message);
    }

    public EntityGenericServerException(Throwable cause) {
        super(cause);
    }
}
