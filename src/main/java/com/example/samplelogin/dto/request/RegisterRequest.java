package com.example.samplelogin.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record RegisterRequest(
        @NotBlank(message = "firstname is blank")
        String firstname,
        @NotBlank(message = "lastname is blank")
        String lastname,
        @NotBlank(message = "username is blank")
        String username,
        @NotBlank(message = "password is blank")
        String password

) {}