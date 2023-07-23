package com.phiwer.utopia.api.infrastructure.configuration;

import com.phiwer.utopia.api.application.SecurityApplication;
import com.phiwer.utopia.api.application.implementation.SecurityApplicationImplementation;
import com.phiwer.utopia.api.domain.model.user.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {
    @Bean
    public SecurityApplication getSecurityApplication(UserRepository userRepository) {
        return new SecurityApplicationImplementation(userRepository);
    }


}
