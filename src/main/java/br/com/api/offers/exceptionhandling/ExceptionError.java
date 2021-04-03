package br.com.api.offers.exceptionhandling;

import lombok.Builder;
import lombok.Data;


@Builder(builderMethodName = "Builder")
@Data
public class ExceptionError {

    protected String timestamp;
    protected int status;
    protected String error;
    protected String message;
    protected String developerMessage;
    protected String path;    
}