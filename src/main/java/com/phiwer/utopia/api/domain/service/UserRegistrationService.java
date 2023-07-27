package com.phiwer.utopia.api.domain.service;

import com.phiwer.utopia.api.domain.model.user.Email;
import com.phiwer.utopia.api.domain.model.user.Role;
import com.phiwer.utopia.api.domain.model.user.User;
import com.phiwer.utopia.api.domain.model.user.UserRepository;
import io.micrometer.common.util.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserRegistrationService {

    public static UserRegistrationResult registerNewUser(final UserRepository userRepository, PasswordEncoder passwordEncoder, final String username, final String email, final String firstName, final String lastName, final String password, final String matchingPassword, Role initialRole) {
        UserRegistrationResult result = new UserRegistrationResult();

        if (StringUtils.isEmpty(username)) {
            result.addError("username", "Username cannot be empty or null");
        }


        if (userRepository.existsByEmailIgnoreCase(email)) {
            result.addError("email", "Email address must be unique");
        }

        if (StringUtils.isEmpty(email)) {
            result.addError("email", "Email cannot be empty or null");
        }

        if (!Email.isValid(email)) {
            result.addError("email", "Email must be properly formatted");
        }

        if (StringUtils.isEmpty(firstName)) {
            result.addError("firstName", "First Name cannot be empty or null");
        }

        if (StringUtils.isEmpty(lastName)) {
            result.addError("lastName", "Last Name cannot be empty or null");
        }

        if (StringUtils.isEmpty(password)) {
            result.addError("password", "Password cannot be empty or null");
        }

        if (StringUtils.isEmpty(matchingPassword)) {
            result.addError("matchingPassword", "Matching password cannot be empty or null");
        }

        if (password.length() > 0 && !password.equals(matchingPassword)) {
            result.addError("matchingPassword", "Matching password must match password");
        }

        if (result.hasErrors()) {
            return result;
        }

        User user = new User(username, email, firstName, lastName, passwordEncoder.encode(password), initialRole);
        userRepository.save(user);

        result.setNewUserId(user.getId());

        return result;
    }
}
