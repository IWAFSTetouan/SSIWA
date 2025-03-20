package univ.iwa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import univ.iwa.filter.JwtRequestFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
@Bean
SecurityFilterChain securityFilterChain(HttpSecurity http, JwtRequestFilter jwtRequestFilter) throws Exception {
http.csrf(csrf->csrf.disable())
.authorizeHttpRequests(authz -> authz
.requestMatchers("/authenticate").permitAll()
.anyRequest().authenticated()
)
.sessionManagement(session -> session
.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
)
.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

return http.build();
}
@Bean
AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
return authenticationConfiguration.getAuthenticationManager();
}}
