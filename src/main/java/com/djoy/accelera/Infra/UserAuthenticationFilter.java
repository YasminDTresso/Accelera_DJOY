package com.djoy.accelera.Infra;

import java.io.IOException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.djoy.accelera.Entity.UsuarioEntity;
import com.djoy.accelera.Repository.UsuarioRepository;
import com.djoy.accelera.Service.JwtTokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

/**
 * A classe UserAuthenticationFilter é um middleware que observa se há a presença do token de
 * autenticação no header de requisições definidas na aplicação.
 **/
@RequiredArgsConstructor
@Component
public class UserAuthenticationFilter extends OncePerRequestFilter {
    // Instancia o serviço que gera o token de autenticação para o usuário
    private final JwtTokenService jwtTokenService;
    // Instancia o repository que fornece o CRUD de usuarios
    private final UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        // Token retornado pela função recoveryToken
        String token = recoveryToken(request);

        // Pula a verificação nas rotas de /usuario e /usuario/login, para que o usuário possa se cadastrar e fazer login sem dar erro 401 ou 403
        if ((request.getRequestURI().equals("/usuario") && request.getMethod().equals("POST")) ||
                (request.getRequestURI().equals("/usuario/login") && request.getMethod().equals("POST"))) {
            filterChain.doFilter(request, response);
            return;
        }

        // Se o token na requisição for válido, entra no if
        if (token != null) {
            // Pega o nome de usuário que foi guardado no corpo do nosso token de autenticação
            String subject = jwtTokenService.getSubjectFromToken(token);

            // Procura se ainda existe um usuário com o nome de usuário capturado
            UsuarioEntity user = usuarioRepository.findByUser(subject).orElse(null);

            // Se o usuário existir, entra no if
            if (user != null) {
                // Instancia a classe UserDetailsImpl, formatando as informações do usuário autenticado 
                UserDetailsImpl userDetails = new UserDetailsImpl(user);

                // Instancia a classe Authentication para definir as informações que serão salvas na sessão do usuário autenticado.
                // No nosso caso, utiliza as informações formatadas em userDetails.  
                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        userDetails.getUsername(), null, userDetails.getAuthorities());

                // Coloca as informações do usúario autenticado na sessão
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } else {
            // Retorna token inválido se o token não for correto
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"erro\": \"Token ausente ou inválido\"}");
            return;
        }

        filterChain.doFilter(request, response);
    }

    // Essa função tenta recuperar a chave "Authorization" do header da requisição HTTP passada como parâmetro
    private String recoveryToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");

        System.out.println(authorizationHeader);

        if (authorizationHeader != null) {
            // Remove a palavra "Bearer" do valor obtido e o retorna
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }
}