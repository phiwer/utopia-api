package com.phiwer.utopia.api.domain.model.user;

import io.micrometer.common.util.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;

public class Email {

    private final String email;

    public Email(final String email) {
        if(StringUtils.isNotEmpty(email)) {
            throw new IllegalArgumentException("email cannot be null or empty");
        }

        this.email = email;
    }

    public static boolean isValid(final String email) {
        return EmailValidator.getInstance()
                .isValid(email);
    }
}
