package week6.java.cogip.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.util.*;
import java.util.stream.Collectors;

// Exception handler to catch exceptions and return all the errors messages
@RestControllerAdvice
public class GlobalExceptionHandler {
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

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Map<String, List<String>>> handleAccessDeniedException(AccessDeniedException ex){
        List<String> errors = Collections.singletonList((ex.getMessage()));
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }

    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }
}
