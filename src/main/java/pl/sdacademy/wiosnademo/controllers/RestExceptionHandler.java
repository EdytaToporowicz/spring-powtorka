package pl.sdacademy.wiosnademo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.sdacademy.wiosnademo.services.UserActionException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice  //  = ControllerAdvice + ResponseBody
public class RestExceptionHandler {
    @ExceptionHandler(UserActionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handle(UserActionException exp) {
        return new ErrorMessage(exp.getMessage(), Map.of());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handle(MethodArgumentNotValidException exp) {
        Map<String, List<String>> details = new HashMap<>();
        List<FieldError> fieldErrors = exp.getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            if (details.containsKey(fieldError.getField())) {
                //kolejny błąd dla danego pola
                details.get(fieldError.getField()).add(fieldError.getDefaultMessage());
            } else {
                //pierwszy błąd dla danego pola
                List<String> errorsMessages = new ArrayList<>();
                errorsMessages.add(fieldError.getDefaultMessage());
                details.put(fieldError.getField(), errorsMessages);
            }
        }
    }
}
