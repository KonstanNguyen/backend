package com.systems.backend.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.systems.backend.constants.JwtConstants;
import com.systems.backend.security.CustomUserDetailsService;
import com.systems.backend.security.JWTAuthenticationFilter;
import com.systems.backend.security.JwtAuthEntryPoint;

import jakarta.servlet.http.HttpServletRequest;

import java.net.http.HttpRequest;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private JwtAuthEntryPoint jwtAuthEntryPoint;
    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                // .cors(null)
                //     t.configurationSource(new CorsConfigurationSource(){
                //     @Override
                //     public CorsConfiguration getCorsConfiguration(){
                //     CorsConfiguration config = new CorsConfiguration();
                //     config.setAllowedOrigins(Arrays.asList("*"));
                //     config.setAllowedMethods(Arrays.asList("*"));
                //     config.setAllowedHeaders(Arrays.asList("*"));
                //     config.setMaxAge(JwtConstants.EXPIRATION_TIME);
                //     config.setAllowCredentials(true);

                //     UrlBasedCorsConfigurationSource source = new
                //     UrlBasedCorsConfigurationSource();
                //     source.registerCo
                //     return config;
                //     }
                //     });
                // })
                .exceptionHandling(httpSecurityExceptionHandlingConfigurer -> {
                    httpSecurityExceptionHandlingConfigurer.authenticationEntryPoint(jwtAuthEntryPoint);
                })
                .sessionManagement(sessionManagement -> {
                    sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .authorizeHttpRequests(authorizeRequests -> {
                    authorizeRequests.anyRequest().permitAll();
                    // .requestMatchers(HttpMethod.GET,"api/documents").permitAll()
                    // .requestMatchers(HttpMethod.GET, "/api/accounts/**").permitAll()
                    // .requestMatchers(HttpMethod.POST, "/api/accounts/**").permitAll()
                    // .requestMatchers(HttpMethod.PUT, "/api/accounts/**").permitAll()
                    // .requestMatchers(HttpMethod.DELETE, "/api/accounts/**").permitAll()
                    // .requestMatchers(HttpMethod.POST, "/api/roles").permitAll()
                    // .anyRequest()
                    // .authenticated();
                            // .requestMatchers(HttpMethod.GET,"api/documents").permitAll()
                            // .requestMatchers(HttpMethod.GET, "/api/accounts/**").permitAll()
                            // .requestMatchers(HttpMethod.POST, "/api/accounts/**").permitAll()
                            // .requestMatchers(HttpMethod.PUT, "/api/accounts/**").permitAll()
                            // .requestMatchers(HttpMethod.DELETE, "/api/accounts/**").permitAll()
                            // .requestMatchers(HttpMethod.POST, "/api/roles").permitAll()
                            // .requestMatchers(HttpMethod.GET,"/api/documents/**").permitAll()
                            // .anyRequest()
                            // .authenticated();
                })
                // .authenticationProvider(authenticationProvider())
                .httpBasic(AbstractHttpConfigurer::disable);
        // http.addFilterBefore(jwtAuthenticationFilter(),
        // UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JWTAuthenticationFilter jwtAuthenticationFilter() {
        return new JWTAuthenticationFilter();
    }

    // @Bean
    // public CorsConfigurationSource corsConfigurationSource() {
    //     CorsConfiguration config = new CorsConfiguration();
    //     config.setAllowedOrigins(Arrays.asList("*"));
    //     config.setAllowedMethods(Arrays.asList("*"));
    //     config.setAllowedHeaders(Arrays.asList("*"));
    //     config.setMaxAge(JwtConstants.EXPIRATION_TIME);
    //     config.setAllowCredentials(true);

    //     UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    //     source.registerCorsConfiguration("/**", config);
    //     return source;
    // }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();

        // Disable SerializationFeature.FAIL_ON_EMPTY_BEANS
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.enable(SerializationFeature.WRITE_SELF_REFERENCES_AS_NULL);
        return objectMapper;
    }
}
