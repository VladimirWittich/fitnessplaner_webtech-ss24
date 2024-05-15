package htwberlin.webtech.ss24.fitnessplaner.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/*")
                .allowedMethods("*")
                .allowedOrigins(
                        "http://localhost:8080",
                        "https://fitnessplaner-frontend-webtech-ss24-e6t9.onrender.com"
                );
    }
}
