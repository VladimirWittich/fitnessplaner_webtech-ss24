package htwberlin.webtech.ss24.fitnessplaner.web;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173", "https://fitnessplaner-frontend-webtech-ss24-e6t9.onrender.com")
                .allowedMethods("*")
                .allowedHeaders("http://localhost:5173");
    }
}
