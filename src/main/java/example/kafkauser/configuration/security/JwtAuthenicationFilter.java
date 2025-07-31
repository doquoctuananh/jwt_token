package example.kafkauser.configuration.security;

import example.kafkauser.model.Jwt.JwtUntil;
import example.kafkauser.service.Implements.ImplCustomeUserDetail;
import example.kafkauser.service.Implements.ImplServiceUser;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenicationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUntil jwtUntil;
    @Autowired
    private ImplCustomeUserDetail implCustomeUserDetail;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

            String token = authHeader.substring(7);
            String username = jwtUntil.getUsername(token);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                System.out.println(123);
                UserDetails userDetails = implCustomeUserDetail.loadUserByUsername(username);
                if(jwtUntil.isTokenExpired(token,username)){
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities()
                    );
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
            filterChain.doFilter(request, response);

    }
}
