package com.phiwer.utopia.api.domain.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserRegistrationResult {
    private final Map<String, String> errors = new HashMap<>();
    private UUID id;

    public void addError(String field, String error) {
        errors.put(field, error);
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public UUID getNewUserId() {
        return id;
    }

    public void setNewUserId(UUID id) {
        this.id = id;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}
