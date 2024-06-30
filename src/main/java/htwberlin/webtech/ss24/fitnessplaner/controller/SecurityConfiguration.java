package htwberlin.webtech.ss24.fitnessplaner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private JwtLoggingFilter jwtLoggingFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests(auth -> {
                    auth
                            .requestMatchers("/bmicalculator").permitAll()
                            .requestMatchers("/workoutplan", "/workoutplan/all", "/workoutplan/search").permitAll()
                            .requestMatchers("/workoutplan/delete/**").authenticated() // Authentifizierung erforderlich für DELETE-Anfragen
                            .requestMatchers("/favicon.ico").permitAll()
                            .anyRequest().authenticated();
                })
                .oauth2Login(withDefaults())
                .formLogin(withDefaults());

        http.addFilterBefore(jwtLoggingFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
