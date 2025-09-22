package it.zusby.ThinkQ.Auth2;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.time.LocalDate;
import java.util.UUID;
@EntityScan(basePackages = "it.zusby.ThinkQ.Auth2")
@Entity @Table(name="users")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class UserEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(unique=true, nullable=false)
    private String email;

    @Column(nullable=false)
    private String password; // BCrypt

    @Column(nullable=false)
    private LocalDate birthday;

    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean enabled = true;
}