package ru.sberdorofeev.bankapi.exception.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.sberdorofeev.bankapi.exception.OpenSessionException;
import ru.sberdorofeev.bankapi.exception.cardExc.CardAlreadyExistsException;
import ru.sberdorofeev.bankapi.exception.invoiceExc.InvoiceAlreadyExistsException;
import ru.sberdorofeev.bankapi.exception.userExc.UserAlreadyExistsException;
import ru.sberdorofeev.bankapi.model.dto.ErrorMessageDto;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    @SuppressWarnings("NullableProblems")
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return handleExceptionInternal(ex, errors, headers, status, request);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Object> handleUserAlreadyExistsException(UserAlreadyExistsException ex,
                                                                   WebRequest request){
        ErrorMessageDto errorMessageDto = new ErrorMessageDto(HttpStatus.CONFLICT.value(), ex.getMessage());
        return handleExceptionInternal(ex, errorMessageDto, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(InvoiceAlreadyExistsException.class)
    public ResponseEntity<Object> handleInvoiceAlreadyExistsException(InvoiceAlreadyExistsException ex,
                                                                   WebRequest request){
        ErrorMessageDto errorMessageDto = new ErrorMessageDto(HttpStatus.CONFLICT.value(), ex.getMessage());
        return handleExceptionInternal(ex, errorMessageDto, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(OpenSessionException.class)
    public ResponseEntity<Object> handleOpenSessionException(InvoiceAlreadyExistsException ex,
                                                                      WebRequest request){
        ErrorMessageDto errorMessageDto = new ErrorMessageDto(HttpStatus.CONFLICT.value(), ex.getMessage());
        return handleExceptionInternal(ex, errorMessageDto, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(CardAlreadyExistsException.class)
    public ResponseEntity<Object> handleCardAlreadyExistsException(InvoiceAlreadyExistsException ex,
                                                             WebRequest request){
        ErrorMessageDto errorMessageDto = new ErrorMessageDto(HttpStatus.CONFLICT.value(), ex.getMessage());
        return handleExceptionInternal(ex, errorMessageDto, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }


}
