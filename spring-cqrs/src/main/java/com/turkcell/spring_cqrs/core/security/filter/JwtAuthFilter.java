package com.turkcell.spring_cqrs.core.security.filter;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.turkcell.spring_cqrs.core.security.context.UserContext;
import com.turkcell.spring_cqrs.core.security.jwt.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Her istekte devreye gir, varsa JWT'i doğrula ve sisteme bak bu kişi şu jwt ile girdi bilgisini tanıt.. 

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserContext userContext;
    
    public JwtAuthFilter(JwtService jwtService, UserContext userContext) {
        this.jwtService = jwtService;
        this.userContext = userContext;
    }

    // her istekte ististanıs gideceğiz ve o istedğin headerından okuyacağız. 
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
                // request -> istek
                // response -> response'ın o ana kadarki oluşan halini 
                // filterChain -> zincirin kendisi
            String jwtHeader = request.getHeader("Authorization");

            if (jwtHeader != null) {
                String token = jwtHeader.substring(7); // ilk 7 hane baerer olduğu için substring ile atıyoruz. "Bearer " kısmını atıyoruz sadece token kalıyor.
                try {
                    if (jwtService.isTokenValid(token)) {
                        String userId = jwtService.extractUserId(token);
                        String email = jwtService.extractEmail(token);
                        List<String> roles = jwtService.extractRoles(token);
                        userContext.setUser(userId, email, roles);
                    }
                } catch (Exception e) {
                    // Geçersiz token -> kullanıcı anonymous kalır
                }
            }

            filterChain.doFilter(request, response); // chaini ilerlet..
    }

}
