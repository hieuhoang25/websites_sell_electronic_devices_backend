package com.poly.datn.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.poly.datn.entity.Account;
import com.poly.datn.entity.RefreshToken;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
  Optional<RefreshToken> findByToken(String token);

//  @Modifying
//  int deleteByAccount(Account account);
//
//  @Modifying
//  int deleteByToken(String token);

//  @Transactional
//  @Modifying
//  @Query(value = "delete from  refreshtoken where account_id = :accountId",nativeQuery = true)
//  int deleteAllTokenByAccountId(@Param("accountId") Integer accountId);

  @Modifying
  @Transactional
  @Query(value = "CALL deleteAllTokenByUserId(:userId)", nativeQuery = true)
  void deleteTokenByUserIdLimit(@Param("userId") Integer userId);
}