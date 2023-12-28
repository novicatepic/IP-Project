package org.unibl.etf.ip.backend.jtwconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.unibl.etf.ip.backend.loginservice.UserService;
import org.unibl.etf.ip.backend.roles.ROLE;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter filter) {
        this.jwtAuthenticationFilter = filter;
    }

    @Autowired
    private UserService userService;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/csrf").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/**")
                        .permitAll()
                        .requestMatchers("/fitness-programs", "/fitness-programs/{id}").permitAll()
                        .requestMatchers("/categories").permitAll()
                        .requestMatchers("/logger").permitAll()
                        .requestMatchers("/rss").permitAll()
                        .requestMatchers("/api/exercises").permitAll()
                        .requestMatchers("/fitness-users/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/questions/{programId}").permitAll()

                        .requestMatchers("/fitness-programs/**").hasRole("USER")
                        .requestMatchers("/category-subscriptions/**").hasRole("USER")
                        .requestMatchers("/messages/**").hasRole("USER")
                        .requestMatchers("/journals/**").hasRole("USER")
                        .requestMatchers("/user-messages/**").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/questions").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/questions/consultants").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/questions/respond").hasRole("USER")
                        .requestMatchers(HttpMethod.PUT, "/fitness-users").hasRole("USER")
                        .anyRequest().authenticated()
                )
                .sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS))
                .authenticationProvider(daoAuthenticationProvider())
                .addFilterBefore(
                        jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {
        return config.getAuthenticationManager();
    }

}
