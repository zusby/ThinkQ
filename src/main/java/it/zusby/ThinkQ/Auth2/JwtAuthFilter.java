package it.zusby.ThinkQ.Auth2;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@Component
@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final JpaUserDetailsService userDetailsService;

    public JwtAuthFilter(JwtUtil jwtUtil, JpaUserDetailsService uds) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = uds;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws ServletException, IOException {

        String path = req.getRequestURI();
        if (path.startsWith("/api/auth/") || path.startsWith("/v3/api-docs") || path.startsWith("/swagger-ui")) {
            chain.doFilter(req, res);
            return;
        }

        String header = req.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            // Nessun token presente → lasciare che Spring chiami JwtAuthEntryPoint
            chain.doFilter(req, res);
            return;
        }

        String token = header.substring(7);

        try {
            var claims = jwtUtil.parseToken(token).getBody();
            String username = claims.getSubject();
            String role = jwtUtil.getRole(token);

            var auth = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    List.of(new SimpleGrantedAuthority("ROLE_" + role))
            );
            SecurityContextHolder.getContext().setAuthentication(auth);

        } catch (RuntimeException e) {
            // Token invalido → cancella il contesto e lascia che Spring chiami JwtAuthEntryPoint
            SecurityContextHolder.clearContext();
            log.error(e.getMessage());
        }

        chain.doFilter(req, res);
    }
}
