package com.poly.datn.repository;

import com.poly.datn.entity.OtpRegister;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpRegisterRepository extends JpaRepository<OtpRegister, String> {
}
