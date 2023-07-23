package com.phiwer.utopia.api.domain.service;

import com.phiwer.utopia.api.domain.model.user.Email;
import com.phiwer.utopia.api.domain.model.user.User;
import com.phiwer.utopia.api.domain.model.user.UserRepository;
import io.micrometer.common.util.StringUtils;

public class UserRegistrationService {

    public static UserRegistrationResult registerNewUser(final UserRepository userRepository, final String email, final String firstName, final String lastName, final String password, final String matchingPassword) {
        UserRegistrationResult result = new UserRegistrationResult();
        if (userRepository.findByEmailIgnoreCase(email).size() > 0) {
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

        if(password.length() > 0 && !password.equals(matchingPassword)) {
            result.addError("matchingPassword", "Matching password must match password");
        }

        if (result.hasErrors()) {
            return result;
        }

        User user = new User(email, firstName, lastName, password);
        userRepository.save(user);

        result.setNewUserId(user.getId());

        return result;
    }
}
