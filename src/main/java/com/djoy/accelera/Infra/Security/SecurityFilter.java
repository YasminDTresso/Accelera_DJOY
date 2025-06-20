package com.djoy.accelera.Infra.Security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.djoy.accelera.Repository.UsuarioRepository;
import com.djoy.accelera.Service.TokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter{

    @Autowired
    TokenService tokenService;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();
        

        System.out.println("Caminho acessado: " + request.getRequestURI());

        // Libera acesso para rotas públicas
        if (path.equals("/auth/view-login") ||
            path.startsWith("/css/") ||
            path.startsWith("/js/") ||
            path.startsWith("/images/") ||
            path.startsWith("/assets/")) {

            System.out.println("Rota pública liberada sem verificação de token: " + path);
            filterChain.doFilter(request, response);
            return;
        }     

        var token = this.recoverToken(request);

        if(token != null){
            var login = tokenService.validateToken(token);

            UserDetails usuario = usuarioRepository.findByLogin(login);
            System.out.println("Resultado da busca: " + usuario);

            if (usuario == null) {
                System.out.println("Usuário não encontrado para login: " + login + "+" + token);

                filterChain.doFilter(request, response);
                return; // Evita erro ao acessar métodos de um objeto null
                }

            // Salvando informações do  usuário necessárias para o Spring Security atuar na filtragem
            var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // Chamando o próximo filtro
        filterChain.doFilter(request, response);

    }

    private String recoverToken(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");
        
        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            return null;
        }
        
        return authHeader.replace("Bearer ", "").trim();
    }

}
