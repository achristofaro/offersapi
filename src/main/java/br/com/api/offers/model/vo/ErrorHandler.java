package br.com.api.offers.model.vo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Data;

@Builder(builderMethodName = "Builder")
@Data
public class ErrorHandler {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String developerMessage;
    private String path;
}