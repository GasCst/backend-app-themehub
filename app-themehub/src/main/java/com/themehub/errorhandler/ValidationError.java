package com.themehub.errorhandler;


import lombok.*;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ValidationError extends SubError{
    private String object;
    private String filled;
    private Object rejectValue;
    private String message;

    public ValidationError( String object, String message){
        this.object = object;
        this.message = message;
    }


}
