package com.phiwer.utopia.api.application.data;

import java.util.HashMap;
import java.util.Map;

public class RegistrationResultData {
    private Map<String, String> errors = new HashMap<>();

    public RegistrationResultData() {

    }

    public RegistrationResultData(final Map<String, String> errors) {
        this.errors = errors;
    }
}
