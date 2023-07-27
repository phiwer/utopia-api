package com.phiwer.utopia.api.web;

import com.phiwer.utopia.api.application.AuthApplication;
import com.phiwer.utopia.api.domain.service.UserRegistrationResult;
import com.phiwer.utopia.api.infrastructure.spring.security.JpaUserDetails;
import com.phiwer.utopia.api.infrastructure.spring.security.jwt.JwtUtils;
import com.phiwer.utopia.api.web.data.LoginRequestData;
import com.phiwer.utopia.api.web.data.LoginResponseData;
import com.phiwer.utopia.api.web.data.UserRegistrationRequestData;
import com.phiwer.utopia.api.web.data.UserRegistrationResponseData;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/")
public class AuthController {

    private final AuthApplication authApplication;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public AuthController(final AuthApplication authApplication,
                          final AuthenticationManager authenticationManager,
                          final JwtUtils jwtUtils) {
        this.authApplication = authApplication;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/auth/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequestData loginRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        JpaUserDetails userDetails = (JpaUserDetails) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity
                .ok(new LoginResponseData(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));
    }

    //@CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/users")
    @ResponseBody
    ResponseEntity<UserRegistrationResponseData> registerUser(@Valid @RequestBody UserRegistrationRequestData userRegistrationRequestData) {
        UserRegistrationResponseData response = new UserRegistrationResponseData();

        UserRegistrationResult result = authApplication.registerNewUser(
                userRegistrationRequestData.getUsername(),
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
