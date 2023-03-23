package com.poly.datn.security.jwt;
import java.time.Instant;
import java.util.ArrayList;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.poly.datn.dto.response.AccountResponse;
import com.poly.datn.dto.response.AuthorityResponse;
import com.poly.datn.security.UserPrincipal;
import com.poly.datn.service.CURDAccountService;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.util.WebUtils;

@Component
@RequiredArgsConstructor
public class JwtUtils {
  private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

  @Value("${spring.app.jwtSecret}")
  private String jwtSecret;

  @Value("${spring.app.jwtCookieName}")
  private String jwtCookie;

  @Value("${spring.app.jwtRefreshCookieName}")
  private String jwtRefreshCookie;
  
  @Value("${spring.app.jwtExpirationMs}")
  private int jwtExpirationMs;
  private final CURDAccountService curdAccountService;

  public String generateJwtToken(UserPrincipal userPrincipal) {
    return generateTokenFromUsername(userPrincipal.getUsername());
  }
  public String getJwtFromCookies(HttpServletRequest request) {
    Cookie cookie = WebUtils.getCookie(request, jwtCookie);
    if (cookie != null) {
      return cookie.getValue();
    } else {
      return null;
    }
  }
  public String getFreshTokenFromCookies(HttpServletRequest request) {
    Cookie cookie = WebUtils.getCookie(request, jwtRefreshCookie);
    if (cookie != null) {
      return cookie.getValue();
    } else {
      return null;
    }
  }
  public ResponseCookie generateJwtCookie(String username) {
    AccountResponse account = curdAccountService.findByUserName(username);
    ArrayList<String> roles = new ArrayList<String>(); 
    for (AuthorityResponse s :  account.getAuthorities()) {
      roles.add(s.getRole_name());
  }

          Algorithm algorithm = Algorithm.HMAC256(jwtSecret.getBytes());
          String access_token = JWT.create().withSubject(account.getUsername())
                  .withExpiresAt(Instant.now().plusSeconds(120))
                  .withClaim("roles",roles)              
                  .sign(algorithm);
    ResponseCookie cookie = ResponseCookie.from(jwtCookie, access_token).path("/api").maxAge(24 * 60 * 60 * 30).httpOnly(true).build();
    return cookie;
  }

  public ResponseCookie getCleanJwtCookie() {
    ResponseCookie cookie = ResponseCookie.from(jwtCookie, null).path("/api").build();
    return cookie;
  }
  public ResponseCookie getCleanJwtRefreshCookie() {
    ResponseCookie cookie = ResponseCookie.from(jwtRefreshCookie, null).path("/api").build();
    return cookie;
  }
  public String getJwtRefreshFromCookies(HttpServletRequest request) {
    return getCookieValueByName(request, jwtRefreshCookie);
  }
  private String getCookieValueByName(HttpServletRequest request, String name) {
    Cookie cookie = WebUtils.getCookie(request, name);
    if (cookie != null) {
      return cookie.getValue();
    } else {
      return null;
    }
  }
 
  public String getUserNameFromJwtToken(String token) {
    return Jwts.parserBuilder().setSigningKey(jwtSecret.getBytes()).build().parseClaimsJws(token).getBody().getSubject();
  }
  public String generateTokenFromUsername(String username) {
    AccountResponse account = curdAccountService.findByUserName(username);
    ArrayList<String> roles = new ArrayList<String>(); 
    for (AuthorityResponse s :  account.getAuthorities()) {
      roles.add(s.getRole_name());
  }
          Algorithm algorithm = Algorithm.HMAC256(jwtSecret.getBytes());
          String access_token = JWT.create().withSubject(account.getUsername())
                  .withExpiresAt(Instant.now().plusSeconds(60*60*24*365))
                  .withClaim("roles",roles)              
                  .sign(algorithm);
          return access_token;
   
  }

  public boolean validateJwtToken(String authToken) {
    try {
      Jwts.parserBuilder().setSigningKey(jwtSecret.getBytes()).build().parseClaimsJws(authToken);
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

  public boolean validateExpiredToken(String authToken){
    try {
      Jwts.parserBuilder().setSigningKey(jwtSecret.getBytes()).build().parseClaimsJws(authToken);
      return false;
    } catch (ExpiredJwtException e) {
        return true;
    }
  }

}
