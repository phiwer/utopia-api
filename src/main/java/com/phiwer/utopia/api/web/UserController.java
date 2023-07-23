package com.phiwer.utopia.api.web;

import com.phiwer.utopia.api.application.SecurityApplication;
import com.phiwer.utopia.api.domain.service.UserRegistrationResult;
import com.phiwer.utopia.api.web.data.UserRegistrationRequestData;
import com.phiwer.utopia.api.web.data.UserRegistrationResponseData;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    private final SecurityApplication securityApplication;

    public UserController(final SecurityApplication securityApplication) {
        this.securityApplication = securityApplication;
    }

    @PostMapping("/users")
    @ResponseBody
    ResponseEntity<UserRegistrationResponseData> registerUser(@Valid @RequestBody UserRegistrationRequestData userRegistrationRequestData) {
        UserRegistrationResponseData response = new UserRegistrationResponseData();

        UserRegistrationResult result = securityApplication.registerNewUser(
                userRegistrationRequestData.getEmail(),
                userRegistrationRequestData.getFirstName(),
                userRegistrationRequestData.getLastName(),
                userRegistrationRequestData.getPassword(),
                userRegistrationRequestData.getMatchingPassword());

        // TODO: Make a converter
        if (result.hasErrors()) {
            response.setErrors(result.getErrors());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        response.setNewUserId(result.getNewUserId().toString());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
