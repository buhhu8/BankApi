package ru.sberdorofeev.bankapi.exception.handler;

import org.hibernate.HibernateException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.sberdorofeev.bankapi.exception.EntityNotFoundException;
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
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        ErrorMessageDto errorMessageDto = new ErrorMessageDto(status.value(), "Invalid data", errors);
        return handleExceptionInternal(ex, errorMessageDto, headers, status, request);
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

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessageDto> handleEntityNotFoundException(EntityNotFoundException ex,
                                                                WebRequest request) {
        ErrorMessageDto errorMessageDto = new ErrorMessageDto(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(errorMessageDto, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorMessageDto errorMessageDto = new ErrorMessageDto(status.value(), "Couldn't deserialize incoming request body");
        return handleExceptionInternal(ex, errorMessageDto, headers, status, request);
    }

    @ExceptionHandler(HibernateException.class)
    public ResponseEntity<ErrorMessageDto> handleHibernateException(HibernateException ex,
                                                                         WebRequest request) {
        ErrorMessageDto errorMessageDto = new ErrorMessageDto(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage()
        );
        return new ResponseEntity<>(errorMessageDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
