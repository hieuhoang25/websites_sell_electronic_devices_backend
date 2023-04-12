package com.poly.datn.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.poly.datn.dto.response.DecodeJWTResponse;
import com.poly.datn.security.UserPrincipal;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

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

    //    public String generateJwtToken(UserPrincipal userPrincipal) {
//        return generateTokenFromUsername(userPrincipal.getUsername());
//    }
//
    public String getJwtFromCookies(HttpServletRequest request) {
        Cookie cookie = WebUtils.getCookie(request, jwtCookie);
        if (cookie != null) {
            return cookie.getValue();
        } else {
            return null;
        }
    }

//    public String getFreshTokenFromCookies(HttpServletRequest request) {
//        Cookie cookie = WebUtils.getCookie(request, jwtRefreshCookie);
//        if (cookie != null) {
//            return cookie.getValue();
//        } else {
//            return null;
//        }
//    }

    public ResponseCookie generateJwtCookie(Authentication authentication) {
        String access_token = createToken(authentication);
        ResponseCookie cookie = ResponseCookie.from(jwtCookie, access_token)
                .path("/api").maxAge(24 * 60 * 60 * 30)
                .httpOnly(true).sameSite("None").secure(true).build();
        return cookie;
    }

    public ResponseCookie generateJwtCookieFromName(String name, List<String> roles) {
        Algorithm algorithm = Algorithm.HMAC256(jwtSecret.getBytes());
        String access_token = JWT.create().withSubject(name)
                .withExpiresAt(Instant.now().plusSeconds(60*60))
                .withClaim("roles", roles)
                .sign(algorithm);
        ResponseCookie cookie = ResponseCookie.from(jwtCookie, access_token)
                .path("/api").maxAge(24 * 60 * 60 * 30)
                .httpOnly(true).sameSite("None").secure(true).build();
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

    public String createToken(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC256(jwtSecret.getBytes());
        String access_token = JWT.create().withSubject(userPrincipal.getUsername())
                .withExpiresAt(Instant.now().plusSeconds(60*60))
                .withClaim("roles", userPrincipal.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .sign(algorithm);
        return access_token;
    }

    public String createRefreshToken(String name) {
        Algorithm algorithm = Algorithm.HMAC256(jwtSecret.getBytes());
        String refresh_token = JWT.create().withSubject(name)
                .withExpiresAt(Instant.now().plusSeconds(24 * 60 * 60 * 30))
                .sign(algorithm);
        return refresh_token;
    }

    public DecodeJWTResponse decodeJWT(String token) throws Exception {
        DecodeJWTResponse jwt = new DecodeJWTResponse();
        Algorithm algorithm = Algorithm.HMAC256(jwtSecret.getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        String username = decodedJWT.getSubject();
        jwt.setUserName(username);
        String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
        if(roles != null){
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            stream(roles).forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));
            jwt.setRoles(authorities);
        }
        return jwt;
    }

//    public boolean validateJwtToken(String authToken) {
//        try {
//            Jwts.parserBuilder().setSigningKey(jwtSecret.getBytes()).build().parseClaimsJws(authToken);
//            return true;
//        } catch (SignatureException e) {
//            logger.error("Invalid JWT signature: {}", e.getMessage());
//        } catch (MalformedJwtException e) {
//            logger.error("Invalid JWT token: {}", e.getMessage());
//        } catch (ExpiredJwtException e) {
//            logger.error("JWT token is expired: {}", e.getMessage());
//        } catch (UnsupportedJwtException e) {
//            logger.error("JWT token is unsupported: {}", e.getMessage());
//        } catch (IllegalArgumentException e) {
//            logger.error("JWT claims string is empty: {}", e.getMessage());
//        }
//        return false;
//    }

    public boolean validateExpiredToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(jwtSecret.getBytes()).build().parseClaimsJws(authToken);
            return false;
        } catch (ExpiredJwtException e) {
            return true;
        }
    }

}
