package it.zusby.ThinkQ.Auth2.Records;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(@NotBlank String username, @NotBlank String password) {}