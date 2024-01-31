package week6.java.cogip.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.*;
import java.util.stream.Collectors;

// Exception handler to catch exceptions and return all the errors messages
@RestControllerAdvice
public class GlobalExceptionHandler {
    // API exceptions handling
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Map<String, List<String>>> handleNotFoundException(NoSuchElementException ex) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DeleteFailedException.class)
    public ResponseEntity<Map<String, List<String>>> handleDeleteFailedException(DeleteFailedException ex){
        List<String> errors = Collections.singletonList((ex.getMessage()));
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler(CreationFailedException.class)
    public ResponseEntity<Map<String, List<String>>> handleCreationFailedException(CreationFailedException ex){
        List<String> errors = Collections.singletonList((ex.getMessage()));
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());
        System.out.println("error");
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }

    // Security exceptions handling
    @ExceptionHandler({UsernameNotFoundException.class, BadCredentialsException.class})
    public ResponseEntity<Object> handleUsernameNotFoundException(Exception ex){
        return new ResponseEntity<>("Username or password is incorrect. " + ex.getMessage(), HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler(InsufficientAuthenticationException.class)
    public ResponseEntity<Object> handleInsufficientAuthenticationException(InsufficientAuthenticationException ex) {
        return new ResponseEntity<>("Login credentials are missing. " + ex.getMessage(), HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler(AccountStatusException.class)
    public ResponseEntity<Object> handleAccountStatusException(AccountStatusException ex) {
        return new ResponseEntity<>("User account is abnormal. " + ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex) {
        return new ResponseEntity<>("No permission. " + ex.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex) {
        return new ResponseEntity<>("This API endpoint is not found. " + ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleOtherException(Exception ex) {
        return new ResponseEntity<> ("A server internal error occurs. " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
