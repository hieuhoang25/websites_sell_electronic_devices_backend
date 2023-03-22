package com.poly.datn.security;

import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;

import com.poly.datn.entity.RefreshToken;
import com.poly.datn.exception.TokenRefreshException;
import com.poly.datn.repository.AccountRepository;
import com.poly.datn.repository.RefreshTokenRepository;

@Service
public class RefreshTokenService {
  @Value("${spring.app.jwtRefreshExpirationMs}")
  private Long refreshTokenDurationMs;

  @Autowired
  private RefreshTokenRepository refreshTokenRepository;

  @Autowired
  private AccountRepository accountRepository;

  public Optional<RefreshToken> findByToken(String token) {
    return refreshTokenRepository.findByToken(token);
  }

  public RefreshToken createRefreshToken(Integer accountId) {
    RefreshToken refreshToken = new RefreshToken();

    refreshToken.setAccount(accountRepository.findById(accountId).get());
    refreshToken.setExpiryDate(Instant.now().plusSeconds(60*60*24*365));
    refreshToken.setToken(UUID.randomUUID().toString());
    refreshToken = refreshTokenRepository.save(refreshToken);
//    refreshTokenRepository.deleteTokenByAccountIdLimit(accountId);
    return refreshToken;
  }

  public RefreshToken verifyExpiration(RefreshToken token) {
    if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
      refreshTokenRepository.delete(token);
      throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
    }

    return token;
  }

  @Transactional
  public int deleteByUserId(Integer accountId) {
    return refreshTokenRepository.deleteByAccount(accountRepository.findById(accountId).get());
  }

  @Transactional
  public int deleteByToken(String token) {
    return refreshTokenRepository.deleteByToken(token);
  }

  public void deleteTokenByAccountIdLimit(Integer accountId){
    refreshTokenRepository.deleteTokenByAccountIdLimit(accountId);
  }
}
