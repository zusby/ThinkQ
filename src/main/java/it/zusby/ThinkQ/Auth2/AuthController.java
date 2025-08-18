package it.zusby.ThinkQ.Auth2;

import it.zusby.ThinkQ.Auth2.Records.LoginRequest;
import it.zusby.ThinkQ.Auth2.Records.LoginResponse;
import it.zusby.ThinkQ.Auth2.Records.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtUtil jwt;
    private final UserRepository repo;
    private final PasswordEncoder encoder;
    private final long expirationMs;

    @Autowired
    public AuthController(AuthenticationManager authManager, JwtUtil jwt, UserRepository repo,
                          PasswordEncoder encoder,
                          @Value("${spring.jwtExpiration}") long expirationMs) {
        this.authManager = authManager;
        this.jwt = jwt;
        this.repo = repo;
        this.encoder = encoder;
        this.expirationMs = expirationMs;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest req) {
        var user = repo.findByEmail(req.username())
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));


        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.username(), req.password())
        );

        if (!auth.isAuthenticated()) {
            throw new BadCredentialsException("Credencials wrong or account not found");
        }

        // genera il token includendo il ruolo enum
        String token = jwt.generateToken(user);

        return ResponseEntity.ok(new LoginResponse(token, expirationMs));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest req) {
        if (repo.existsByEmail(req.username())) {
            return ResponseEntity.badRequest().body("Username already exists");
        }
        if (req.password().length() < 6) {
            return ResponseEntity.badRequest().body("Password too weak");
        }
        if (req.birthday() == null) {
            return ResponseEntity.badRequest().body("Birthday is required");
        }

        Role role = (req.role() == null) ? Role.USER : Role.valueOf(req.role().toUpperCase());

        var u = UserEntity.builder()
                .email(req.username())
                .password(encoder.encode(req.password()))
                .birthday(req.birthday())
                .role(role)
                .enabled(true)
                .build();

        repo.save(u);

        return ResponseEntity.ok("Registered");
    }
}