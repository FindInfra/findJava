package com.find.findcore.model.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ResetRequest {
    @NotBlank(message = "provider cannot be empty")
    private String provider;

    @NotBlank
    @Size(min = 3, max = 120, message = "email cannot be empty and be between {min} and {max} characters")
    private String email;

    @NotBlank
    @Size(min = 4, max = 120, message = "password cannot be empty and should be between {min} and {max} characters")
    private String password;

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}