package it.zusby.ThinkQ.Auth2.Records;

import it.zusby.ThinkQ.Auth2.Role;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record RegisterRequest(@NotBlank String username, @NotBlank String password, String role, LocalDate birthday) {

}