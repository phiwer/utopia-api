package com.phiwer.utopia.api.application.implementation;

import com.phiwer.utopia.api.application.SecurityApplication;
import com.phiwer.utopia.api.domain.model.user.UserRepository;
import com.phiwer.utopia.api.domain.service.UserRegistrationResult;
import com.phiwer.utopia.api.domain.service.UserRegistrationService;

public class SecurityApplicationImplementation implements SecurityApplication {

    private final UserRepository userRepository;

    public SecurityApplicationImplementation(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserRegistrationResult registerNewUser(final String email, final String firstName, final String lastName, final String password, final String passwordAgain) {
        return UserRegistrationService.registerNewUser(userRepository, email, firstName, lastName, password, passwordAgain);
    }
}
