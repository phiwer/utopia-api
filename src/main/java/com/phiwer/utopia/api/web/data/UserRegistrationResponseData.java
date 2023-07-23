package com.phiwer.utopia.api.web.data;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.HashMap;
import java.util.Map;

public class UserRegistrationResponseData {

    private Map<String, String> errors = new HashMap<>();
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String newUserId;

    public String getNewUserId() {
        return newUserId;
    }

    public void setNewUserId(String newUserId) {
        this.newUserId = newUserId;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void addError(final String field, final String error) {
        this.errors.put(field, error);
    }

    public boolean hasErrors() {
        return !this.errors.isEmpty();
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
}
