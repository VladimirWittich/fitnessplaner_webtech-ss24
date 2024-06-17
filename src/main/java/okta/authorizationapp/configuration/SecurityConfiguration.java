package okta.authorizationapp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    @Order(1)
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())) // CSRF-Schutz aktivieren
                .cors(Customizer.withDefaults()) // CORS-Konfiguration hinzufügen
                .authorizeRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/oauth2/**").permitAll() // Anfragen, die mit /oauth2/ beginnen, erlauben
                        .anyRequest().authenticated()) // Alle anderen Anfragen erfordern Authentifizierung
                .oauth2Login(oauth2Login -> oauth2Login
                        .loginPage("/oauth2/authorization/okta") // Login-Seite für Okta OAuth2
                        .defaultSuccessUrl("/")) // Erfolgreiche Weiterleitung nach dem Login
                .oauth2Client(Customizer.withDefaults()) // Standard-OAuth2-Client-Konfiguration
                .oauth2ResourceServer(oauth2ResourceServer -> oauth2ResourceServer
                        .jwt(Customizer.withDefaults())); // Standard-JWT-Konfiguration

        return http.build();
    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Collections.singletonList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }


}
