//package it.zusby.ThinkQ.Config;
//
//
//import it.zusby.ThinkQ.Auth2.UserRepository;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//@Configuration
//public class AppConfig {
//    private final UserRepository userRepository;
//
//    public AppConfig(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Bean
//    public String userDetailsService() {
//        return username ->
//                userRepository.findByEmail(username)
//                .orElseThrow(() -> new UsernameNotFoundException(username));
//
//    }
//}
