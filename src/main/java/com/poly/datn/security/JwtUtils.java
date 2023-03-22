package com.poly.datn.security;


import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.poly.datn.dto.response.AccountResponse;
import com.poly.datn.dto.response.AuthorityResponse;
import com.poly.datn.entity.Account;
import com.poly.datn.entity.Authority;
import com.poly.datn.repository.AccountRepository;
import com.poly.datn.service.CURDAccountService;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtUtils {
  private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

  @Value("${spring.app.jwtSecret}")
  private String jwtSecret;

  @Value("${spring.app.jwtExpirationMs}")
  private int jwtExpirationMs;
  private final CURDAccountService curdAccountService;
  public String generateJwtToken(UserPrincipal userPrincipal) {
    return generateTokenFromUsername(userPrincipal.getUsername());
  }

  public String generateTokenFromUsername(String username) {
    AccountResponse account = curdAccountService.findByUserName(username);
    ArrayList<String> roles = new ArrayList<String>(); 
    for (AuthorityResponse s :  account.getAuthorities()) {
      roles.add(s.getRole_name());
  }
  //   return Jwts.builder().setSubject(username).setIssuedAt(new Date()).claim("roles", roles)
  //       .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs)).signWith(SignatureAlgorithm.HS512, generateSafeToken())
  //       .compact();

          Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
          String access_token = JWT.create().withSubject(account.getUsername())
                  .withExpiresAt(Instant.now().plusSeconds(60*60*24*365))
                  .withClaim("roles",roles)              
                  .sign(algorithm);
          return access_token;
   
  }
  private SecretKey generateSafeToken() {
    SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    return key;
}
  public String getUserNameFromJwtToken(String token) {
    return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
  }

  public boolean validateJwtToken(String authToken) {
    try {
      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
      return true;
    } catch (SignatureException e) {
      logger.error("Invalid JWT signature: {}", e.getMessage());
    } catch (MalformedJwtException e) {
      logger.error("Invalid JWT token: {}", e.getMessage());
    } catch (ExpiredJwtException e) {
      logger.error("JWT token is expired: {}", e.getMessage());
    } catch (UnsupportedJwtException e) {
      logger.error("JWT token is unsupported: {}", e.getMessage());
    } catch (IllegalArgumentException e) {
      logger.error("JWT claims string is empty: {}", e.getMessage());
    }

    return false;
  }

}
