package ds.course.group.fiftyone.blooddonor.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean (AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();

    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        final CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedHeaders(
                List.of("Authorization", "Cache-Control", "Content-Type"));
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:5173"));
        corsConfiguration
                .setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PUT", "OPTIONS", "PATCH", "DELETE"));
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setExposedHeaders(List.of("Authorization"));

        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(request -> corsConfiguration))
                .addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint(unauthorizedHandler))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/v2/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html"
                        ).permitAll()
                        // Citizen api
                        .requestMatchers("/api/citizen/user/**").hasRole("USER")
                        .requestMatchers("/api/citizen/change-email").hasRole("USER")
                        .requestMatchers("/api/citizen/**").hasAnyRole("ADMIN", "MODERATOR")
                        // Donor application api
                        .requestMatchers("/api/donor-application/create").hasRole("USER")
                        .requestMatchers("/api/donor-application/review/**").hasAnyRole("ADMIN", "MODERATOR")
                        .requestMatchers("/api/donor-application/unreviewed/**").hasAnyRole("ADMIN", "MODERATOR")
                        .requestMatchers("/api/donor-application/view/**").hasAnyRole("ADMIN", "MODERATOR")
                        // Notification api
                        .requestMatchers("/api/notification/get/**").hasRole("USER")
                        .requestMatchers("/api/notification/**").hasAnyRole("ADMIN", "MODERATOR")
                        .anyRequest().authenticated()
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }



}

/*
//.requestMatchers("/citizen/**").hasRole("USER")
                        .requestMatchers("/api/citizen/**").authenticated()
                        //.requestMatchers("/api/citizen").hasRole("USER")
                        .anyRequest().authenticated()
                        //.anyRequest().permitAll()
 */
