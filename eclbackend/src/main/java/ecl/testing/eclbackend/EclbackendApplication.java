package ecl.testing.eclbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class EclbackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(EclbackendApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        // To allow the CORS for localhost and 127.0.0.1, otherwise, the FrontEnd would be blocked
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:10086", "http://127.0.0.1:10086")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*");
            }
        };
    }
}
