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

/**
 * A classe SecurityConfiguration é importante para nós, pois através dela podemos adicionar 
 * proteção nas rotas, podendo também definir regras de usuário 
 **/
@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfiguration {
        // Instancia o middleware que verifica se o token de autenticação está na requsição HTTP
        private final UserAuthenticationFilter userAuthenticationFilter;
        // Instancia a exceção do erro HTTP 403 customizada
        private final ForbiddenException forbiddenException;
        // Instancia a exceção do erro HTTP 401 customizada
        private final AuthorizationException authorizationException;


        // Nessa função definimos quais rotas serão protegidas e quais serão as regras para essas rotas
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                return http
                                .csrf(csrf -> csrf.disable())
                                .sessionManagement(session -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                // Aplica as exceções HTTP que eu instanciei lá em cima
                                .exceptionHandling(exception -> exception
                                                .accessDeniedHandler(forbiddenException)
                                                .authenticationEntryPoint(authorizationException))
                                // Define quais rotas são protegidas, quais regras são necessárias para elas, etc.
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
                                // Aplica o middleware que verifica se o token de autorização está presente na requisição HTTP
                                .addFilterBefore(userAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                                .build();
        }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
                        throws Exception {
                return authenticationConfiguration.getAuthenticationManager();
        }

        // Esse método utliza o bcrypt como biblioteca de criptografia para criptografar a senha dos usuários
        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }
}
