package com.poly.datn.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import com.poly.datn.security.CustomUserDetailsService;
import groovy.util.logging.Slf4j;

@Slf4j
public class AuthTokenFilter extends OncePerRequestFilter {
          @Autowired
          private JwtUtils jwtUtils;
          @Value("${spring.app.jwtSecret}")
          private String jwtSecret;
          @Autowired
          private CustomUserDetailsService userPrincipal;


        
          private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);
        
          @Override
          protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
              throws ServletException, IOException {
         
                String jwt = parseJwt(request);         
            try {
              if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
                String username = jwtUtils.getUserNameFromJwtToken(jwt);
                UserDetails userDetails = userPrincipal.loadUserByUsername(username);
                System.out.println(userDetails.getAuthorities());
                UsernamePasswordAuthenticationToken authentication = 
                    new UsernamePasswordAuthenticationToken(userDetails,
                                                            null,
                                                            userDetails.getAuthorities());
                
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        
                SecurityContextHolder.getContext().setAuthentication(authentication);
        
              }
            } catch (Exception e) {
              logger.error("Cannot set user authentication: {}", e);
            }
        
            filterChain.doFilter(request, response);
          }
        
          private String parseJwt(HttpServletRequest request) {
            String jwt = jwtUtils.getJwtFromCookies(request);
            return jwt;
          }
        
        }
        
