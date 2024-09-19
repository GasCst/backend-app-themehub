package com.themehub.errorhandler;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
@ToString
public class EntityGenericClientException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private HttpStatus httpStatus;
    private List<SubError> subErrors;

    public EntityGenericClientException( String message, HttpStatus httpStatus){
        super(message);
        this.httpStatus=httpStatus;
    }

    public EntityGenericClientException() {
    }

    public EntityGenericClientException(String message) {
        super(message);
    }

    public EntityGenericClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityGenericClientException(Throwable cause) {
        super(cause);
    }

    public EntityGenericClientException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
