package com.phiwer.utopia.api.domain.model.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {

    Optional<User> findByUsername(final String username);

    Boolean existsByUsernameIgnoreCase(final String username);

    Boolean existsByEmailIgnoreCase(final String email);
}
