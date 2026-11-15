package com.priyansh.cinebook.DTO.Auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterRequestDTO(
        @NotBlank @Size(min = 3, max = 50) String username,
        @NotBlank @Size(max = 100) String name,
        @NotBlank @Email @Size(max = 254) String email,
        @NotNull @Min(1) @Max(120) Integer age,
        @NotBlank @Pattern(regexp = "\\+?[0-9]{7,15}") String number,
        @NotBlank @Size(min = 8, max = 128) String password) {
}