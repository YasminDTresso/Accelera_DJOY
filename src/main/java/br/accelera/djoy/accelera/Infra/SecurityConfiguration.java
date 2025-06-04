package br.accelera.djoy.accelera.Infra;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.accelera.djoy.accelera.Infra.exceptions.AuthorizationException;
import br.accelera.djoy.accelera.Infra.exceptions.ForbiddenException;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfiguration {
        private final UserAuthenticationFilter userAuthenticationFilter;
        private final ForbiddenException forbiddenException;
        private final AuthorizationException authorizationException;

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                return http
                                .csrf(csrf -> csrf.disable())
                                .sessionManagement(session -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .exceptionHandling(exception -> exception
                                                .accessDeniedHandler(forbiddenException)
                                                .authenticationEntryPoint(authorizationException))
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers(HttpMethod.POST, "/usuario").permitAll()
                                                .requestMatchers(HttpMethod.POST, "/usuario/login").permitAll()
                                                .requestMatchers(HttpMethod.GET, "/usuario").hasRole("ADMINISTRADOR")
                                                .requestMatchers("/usuario/**").hasRole("ADMINSTRADOR")

                                                .requestMatchers("/log-trabalho-pendente").hasRole("ADMINISTRADOR")
                                                .requestMatchers("/log-consulta").hasRole("ADMINISTRADOR")
                                                .requestMatchers("/usuario-transportadora").hasRole("ADMINISTRADOR")

                                                .requestMatchers("/transportadora")
                                                .hasAnyRole("ADMINISTRADOR", "GERENTE")
                                                .requestMatchers("/consulta-transportadora")
                                                .hasAnyRole("ADMINISTRADOR", "GERENTE", "COLABORADOR")
                                                .requestMatchers("/controle-checklist")
                                                .hasAnyRole("ADMINISTRADOR", "GERENTE", "COLABORADOR")

                                                .anyRequest().authenticated())
                                .addFilterBefore(userAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                                .build();
        }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
                        throws Exception {
                return authenticationConfiguration.getAuthenticationManager();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }
}
