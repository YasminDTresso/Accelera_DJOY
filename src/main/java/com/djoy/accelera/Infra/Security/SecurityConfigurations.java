package com.djoy.accelera.Infra.Security;

import org.springframework.beans.factory.annotation.Autowired;
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

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {
    @SuppressWarnings("unused")

    @Autowired
    SecurityFilter securityFilter;
    
    // Método de validação para requisições do usuário
    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
               //Desligando a configuração padrão
               .csrf(csrf -> csrf.disable())
              //Definindo para Stateless
               .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
              //Definindo requisições autorizadas
              .authorizeHttpRequests(authorize -> authorize
              // Permitir que todos os usuários acessem a pagina de login
                .requestMatchers(HttpMethod.POST,"/auth/login").permitAll()
                .requestMatchers(HttpMethod.POST,"/auth/cadastrar-usuario").permitAll()
                .requestMatchers(
                    HttpMethod.GET,
                    "/auth/view-login", "/css/**", "/js/**", "/images/**", "/assets/**", "/home"
                ).permitAll()
              .requestMatchers(HttpMethod.POST, "/pessoa").hasRole("ADMINISTRADOR")
              //O resto dos metodos pode ser requisitado por qualquer usuário autenticado
              .anyRequest().authenticated()
              )
              //Filtro de validação do token 
              .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
               .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    // Encriptador de senha
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
