package com.student.expense_tracker.security;

import com.student.expense_tracker.model.User;
import com.student.expense_tracker.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.Collections;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private UserRepository userRepository;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader=request.getHeader("Authorization");
        if(authHeader!=null &&  authHeader.startsWith("Bearer ")){
            String token=authHeader.substring(7);
            if(jwtUtil.validateToken(token)){
                String username=jwtUtil.getUsernameFromToken(token);
                User user=userRepository.findByUsername(username).orElse(null);
                if(user!=null){
                    UsernamePasswordAuthenticationToken authentication=new UsernamePasswordAuthenticationToken(user,null,Collections.emptyList());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        filterChain.doFilter(request,response);
    }
}
