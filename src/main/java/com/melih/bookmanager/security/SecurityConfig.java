package com.melih.bookmanager.security;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/user/register", "/user/login", "/error","/v3/api-docs",
                                "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                        .requestMatchers(HttpMethod.POST, "/books/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/books/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/books/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/books/**").hasAnyRole("USER", "ADMIN")
                        .anyRequest().authenticated()
                )

                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("BookManager API")
                        .version("1.0")
                        .description("## 📚 Welcome to the BookManager API!\n\n" +
                                "This API allows you to manage a personal library, track your reading progress, and manage user accounts.\n\n" +
                                "### 🔐 Test Credentials\n" +
                                "* **Administrator:** `admin` / `admin` (Full access, including DELETE operations)\n" +
                                "* **Standard User:** `user` / `user` (Manage personal lists and read books)\n\n" +
                                "### 🚀 Quick Start\n" +
                                "1. Click the **'Authorize'** button on the right.\n" +
                                "2. Log in with one of the test accounts above.\n" +
                                "3. Start exploring the endpoints!"))
                .addSecurityItem((new SecurityRequirement().addList("BasicAuth")))
                .components(new Components()
                        .addSecuritySchemes("BasicAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("basic")));
    }
}
