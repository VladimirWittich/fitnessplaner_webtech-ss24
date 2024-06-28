package htwberlin.webtech.ss24.fitnessplaner.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import static org.springframework.security.config.Customizer.*;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/workoutplan/history").authenticated() // Zugriff nur für authentifizierte Benutzer
                                .anyRequest().permitAll() // Alle anderen Anfragen sind für alle Benutzer erlaubt
                )
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/login") // Benutzerdefinierte Login-Seite
                                .usernameParameter("email") // Parametername für den Benutzernamen im Login-Formular
                                .permitAll()
                )
                .oauth2Login(withDefaults()) // Standardkonfiguration für OAuth2-Login
                .csrf(csrf ->
                        csrf
                                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()) // CSRF-Schutz mit Cookie-basiertem Token
                )
                .logout(logout ->
                        logout
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/")
                                .permitAll()
                );

        return http.build();
    }

}
