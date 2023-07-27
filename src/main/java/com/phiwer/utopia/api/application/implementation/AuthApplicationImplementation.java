package com.phiwer.utopia.api.application.implementation;

import com.phiwer.utopia.api.application.AuthApplication;
import com.phiwer.utopia.api.domain.model.user.Role;
import com.phiwer.utopia.api.domain.model.user.RoleRepository;
import com.phiwer.utopia.api.domain.model.user.UserRepository;
import com.phiwer.utopia.api.domain.service.UserRegistrationResult;
import com.phiwer.utopia.api.domain.service.UserRegistrationService;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AuthApplicationImplementation implements AuthApplication {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthApplicationImplementation(final UserRepository userRepository,
                                         final RoleRepository roleRepository,
                                         final PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserRegistrationResult registerNewUser(final String username, final String email, final String firstName, final String lastName, final String password, final String passwordAgain) {
        Role userRole = roleRepository.findByName("User");
        return UserRegistrationService.registerNewUser(userRepository, passwordEncoder, username, email, firstName, lastName, password, passwordAgain, userRole);
    }
}
