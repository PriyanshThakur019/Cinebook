package com.priyansh.cinebook.DTO.Auth;

public record AuthUserResponseDTO(
        Long userDetailsId,
        String username,
        String name,
        String email,
        Integer age,
        String mobile) {
}