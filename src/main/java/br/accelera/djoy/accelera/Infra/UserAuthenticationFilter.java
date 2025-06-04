package br.accelera.djoy.accelera.Infra;

import java.io.IOException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.accelera.djoy.accelera.Entities.UsuarioEntity;
import br.accelera.djoy.accelera.Repositories.UsuarioRepository;
import br.accelera.djoy.accelera.Services.JwtTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class UserAuthenticationFilter extends OncePerRequestFilter {
    private final JwtTokenService jwtTokenService;
    private final UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = recoveryToken(request);

        if ((request.getRequestURI().equals("/usuario") && request.getMethod().equals("POST")) ||
                (request.getRequestURI().equals("/usuario/login") && request.getMethod().equals("POST"))) {
            filterChain.doFilter(request, response);
            return; 
        }

        if (token != null) {
            String subject = jwtTokenService.getSubjectFromToken(token);
            UsuarioEntity user = usuarioRepository.findByUsuario(subject).orElse(null);

            if (user != null) {
                UserDetailsImpl userDetails = new UserDetailsImpl(user);
                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        userDetails.getUsername(), null, userDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"erro\": \"Token ausente ou inválido\"}");
            return;
        }

        filterChain.doFilter(request, response);
    }

    private String recoveryToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");

        System.out.println(authorizationHeader);

        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }
}