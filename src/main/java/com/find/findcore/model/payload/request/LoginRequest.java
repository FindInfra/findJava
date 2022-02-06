package com.find.findcore.model.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LoginRequest {
    private String username;

    @NotBlank
    @Size(min = 3, max = 120, message = "email cannot be empty and be between {min} and {max} characters")
    private String email;

    @NotBlank
    @Size(min = 4, max = 120, message = "password cannot be empty and should be between {min} and {max} characters")
    private String password;

    @NotBlank(message = "provider cannot be empty")
    private String provider;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }
}