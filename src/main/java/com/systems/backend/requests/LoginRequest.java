package com.systems.backend.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class LoginRequest extends ApiRequest {
    @NotBlank(message = "Username is required!")
    public String username;
    @NotBlank(message = "Password is required!")
    public String password;
}
