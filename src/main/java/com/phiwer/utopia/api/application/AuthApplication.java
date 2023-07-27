package com.phiwer.utopia.api.application;

import com.phiwer.utopia.api.domain.service.UserRegistrationResult;
import jakarta.transaction.Transactional;

public interface AuthApplication {

    @Transactional
    UserRegistrationResult registerNewUser(final String username, final String email, final String firstName, final String lastName, final String password, final String matchingPassword);
}
