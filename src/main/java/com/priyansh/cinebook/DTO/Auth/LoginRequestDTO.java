package com.priyansh.cinebook.DTO.Auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequestDTO(
        @NotBlank @Size(max = 50) String username,
        @NotBlank @Size(max = 128) String password) {
}