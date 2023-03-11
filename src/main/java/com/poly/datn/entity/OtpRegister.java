package com.poly.datn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "opt_register")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OtpRegister {
    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "full_name")
    private String fullName;


    @Column(name = "phone", length = 13)
    private String phone;





    @Column(name = "password")
    private String password;



    @Column(name = "otp_code")
    private String otpCode;


    @Column(name = "time_expire")
    private LocalDateTime timeExpire;

    @Column(name = "is_verified")
    private Boolean isVerified;

}
