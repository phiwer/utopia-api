package com.phiwer.utopia.api.infrastructure.spring.configuration;

import com.phiwer.utopia.api.application.AuthApplication;
import com.phiwer.utopia.api.application.implementation.AuthApplicationImplementation;
import com.phiwer.utopia.api.domain.model.user.RoleRepository;
import com.phiwer.utopia.api.domain.model.user.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ApplicationConfiguration {
    @Bean
    public AuthApplication getAuthApplication(UserRepository userRepository,
                                              RoleRepository roleRepository,
                                              PasswordEncoder passwordEncoder) {
        return new AuthApplicationImplementation(userRepository, roleRepository, passwordEncoder);
    }


}
