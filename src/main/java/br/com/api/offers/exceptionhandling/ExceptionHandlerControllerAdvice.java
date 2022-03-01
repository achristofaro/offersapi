package br.com.api.offers.exceptionhandling;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.api.offers.model.vo.ErrorHandler;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice extends ResponseEntityExceptionHandler {

    
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleResourceNotFoundException(NotFoundException ex, HttpServletRequest req) {


        
        ErrorHandler exError = ErrorHandler.Builder()
            .timestamp(LocalDateTime.now())
            .status(HttpStatus.NOT_FOUND.value())
            .error(HttpStatus.NOT_FOUND.getReasonPhrase())
            .message(ex.getMessage())
            .developerMessage(ex.getClass().getName())
            .path(req.getRequestURI())                
            .build();
                

        return new ResponseEntity<>(exError, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler({Exception.class, RuntimeException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleException(Exception ex, HttpServletRequest req) {
        
        ErrorHandler exError = ErrorHandler.Builder()
            .timestamp(LocalDateTime.now())
            .status(HttpStatus.NOT_FOUND.value())
            .error(HttpStatus.NOT_FOUND.getReasonPhrase())
            .message(ex.getMessage())
            .developerMessage(ex.getClass().getName())
            .path(req.getRequestURI())                
            .build();

        return new ResponseEntity<>(exError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}