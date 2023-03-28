package com.poly.datn.security;

import com.poly.datn.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;

import com.poly.datn.entity.RefreshToken;
import com.poly.datn.exception.TokenRefreshException;
import com.poly.datn.repository.AccountRepository;
import com.poly.datn.repository.RefreshTokenRepository;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    @Value("${spring.app.jwtRefreshExpirationMs}")
    private Long refreshTokenDurationMs;
    @Value("${spring.app.jwtRefreshCookieName}")
    private String jwtRefreshCookie;

    private final RefreshTokenRepository refreshTokenRepository;

    private final AccountRepository accountRepository;

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public ResponseCookie generateRefreshTokenCookie(User user,String refresh_token) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(user);
        refreshToken.setExpiryDate(Instant.now().plusSeconds(60 * 60 * 24 * 30));
        refreshToken.setToken(refresh_token);
        refreshToken = refreshTokenRepository.save(refreshToken);
        ResponseCookie cookie = ResponseCookie.from
                        (jwtRefreshCookie, refreshToken.getToken()).path("/api").
                maxAge(24 * 60 * 60 * 30).httpOnly(true).sameSite("None").secure(true).build();
        return cookie;
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
        }

        return token;
    }

//    @Transactional
//    public int deleteByUserId(Integer accountId) {
//        return refreshTokenRepository.deleteByAccount(accountRepository.findById(accountId).get());
//    }
//
//    @Transactional
//    public int deleteByToken(String token) {
//        return refreshTokenRepository.deleteByToken(token);
//    }

    public void deleteTokenByUserIdLimit(Integer userId) {
        refreshTokenRepository.deleteTokenByUserIdLimit(userId);
    }
}
