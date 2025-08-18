package it.zusby.ThinkQ.Auth2.Records;


import jakarta.validation.constraints.NotBlank;

public record LoginResponse(String token, long expiresInMs) {}