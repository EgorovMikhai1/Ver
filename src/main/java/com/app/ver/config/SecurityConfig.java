package com.app.ver.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/cars/getByBrand/**").hasRole("USER")
                        .requestMatchers("/cars/getByModel/**").hasRole("ADMIN")
                        .requestMatchers("/cars/getAll").permitAll()
                        .anyRequest().authenticated())

                .formLogin(withDefaults())
                .httpBasic(withDefaults())
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider provider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        String encodedAdminPassword = "$2a$10$kacdvfcepw3hOwdSkF5Hp.jLUVHgaFY3OsX4IM5KWyFUk.JTpInzy"; // "admin"
        String encodedUserPassword = "$2a$10$CvpFgC6Lka0MuUs8XCdmpustf/x7iumq8428VGVzdKGN7KvySy16O"; // "user"

        UserDetails user = User.builder()
                .username("user")
                .password(encodedUserPassword)
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(encodedAdminPassword)
                .roles("ADMIN")
                .build();

        System.out.println("User roles: " + user.getAuthorities());
        System.out.println("Admin roles: " + admin.getAuthorities());

        return new InMemoryUserDetailsManager(user, admin);
    }
}