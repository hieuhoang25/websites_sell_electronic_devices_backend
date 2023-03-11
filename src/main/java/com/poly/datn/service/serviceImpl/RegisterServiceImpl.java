package com.poly.datn.service.serviceImpl;

import com.poly.datn.dto.request.RegisterRequest;
import com.poly.datn.dto.response.MessageVerification;
import com.poly.datn.entity.Account;
import com.poly.datn.entity.Authority;
import com.poly.datn.entity.OtpRegister;
import com.poly.datn.entity.User;
import com.poly.datn.exception.BadRequestException;
import com.poly.datn.repository.*;
import com.poly.datn.service.AuthorityService;
import com.poly.datn.service.MailService;
import com.poly.datn.service.RegisterService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@AllArgsConstructor
@Slf4j
public class RegisterServiceImpl implements RegisterService {
    private OtpRegisterRepository otpRegisterRepository;
    private UserRepository userRepository;

    private AccountRepository accountRepository;
    private PasswordEncoder passwordEncoder;

    private  AuthorityRepository authorityRepository;
    private RoleRepository roleRepository ;
    private MailService mailService;
    @Override
    public MessageVerification register(RegisterRequest registerRequest) {
        User user = userRepository.findByEmail(registerRequest.getEmail()).orElse(null);
        Account account = accountRepository.findByUsername(registerRequest.getUsername());
        OtpRegister otpRegister = otpRegisterRepository.findById(registerRequest.getEmail()).orElse(null);
        if (user !=null || account!=null) return new MessageVerification("Tài khoản đã được đăng ký", 1);
        else{
            if (otpRegister==null){
                OtpRegister or = new OtpRegister();
                or.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
                or.setUsername(registerRequest.getUsername());
                or.setPhone(registerRequest.getPhone());
                or.setFullName(registerRequest.getFullName());
                or.setEmail(registerRequest.getEmail());
                or.setIsVerified(false);
                or.setTimeExpire(LocalDateTime.now().plusMinutes(5));
                or.setOtpCode(otpCode()+"");
                // send otp
                mailService.sendEmailVerification(or.getOtpCode(), or.getEmail());
                otpRegisterRepository.save(or);
                return new MessageVerification("Vui lòng check mail OTP đã được gửi", 1);
            }
            else{
                if (otpRegister.getIsVerified()==false){
                    // otp expired
                    if (otpRegister.getTimeExpire().compareTo(LocalDateTime.now()) < 0){
                        // resend token
                        otpRegister.setTimeExpire(LocalDateTime.now().plusMinutes(5));
                        otpRegister.setOtpCode(otpCode()+"");
                        otpRegisterRepository.save(otpRegister);
                        mailService.sendEmailVerification(otpRegister.getOtpCode(), otpRegister.getEmail());
                        return new MessageVerification("Vui lòng check mail OTP đã được gửi", 1);
                    }
                    else{
                        // check mail please
                        return new MessageVerification("Vui lòng check mail OTP đã được gửi", 1);
                    }
                }
                else{
                    otpRegisterRepository.delete(otpRegister);
                    return new MessageVerification("Tài khoản đã được đăng ký", 1);
                }
            }
        }
    }

    @Override
    public MessageVerification verifyOtp(String otp, String username) {
        OtpRegister otpRegister = otpRegisterRepository.findById(username).orElseThrow(() -> new UsernameNotFoundException("Mã OTP hết hạn hoặc không chính xác"));

        String otpR = otpRegister.getOtpCode();
        LocalDateTime timeExpire = otpRegister.getTimeExpire();

        if (otpR.equalsIgnoreCase(otp) && timeExpire.compareTo(LocalDateTime.now()) > 0 ){
            // Save user, account
            User user = new User();
            user.setEmail(otpRegister.getEmail());
            user.setFullName(otpRegister.getFullName());
            user.setPhone(otpRegister.getPhone());
            user =  userRepository.save(user);
            Account account = new Account();
            account.setActive(true);
            account.setUser(user);
            account.setUsername(otpRegister.getUsername());
            account.setPassword(otpRegister.getPassword());
            account =  accountRepository.save(account);
            Authority authority = new Authority();
            authority.setRole(roleRepository.findById(1).orElseThrow());
            authority.setAccount(account);
            authorityRepository.save(authority);
            // delete after verifing otp
            otpRegisterRepository.delete(otpRegister);
            return new MessageVerification("Đăng ký thành công",1);
        }
        else{
            //resend
            return new MessageVerification("Mã OTP không chính xác hoặc hết hạn",0);
        }
    }

    @Override
    public MessageVerification reSendOTP(String username) {
        OtpRegister otpRegister = otpRegisterRepository.findById(username).orElseThrow(() -> new UsernameNotFoundException("Email chưa được đăng ký"));
        otpRegister.setTimeExpire(LocalDateTime.now().plusMinutes(5));
        otpRegister.setOtpCode(otpCode()+"");
        mailService.sendEmailVerification(otpRegister.getOtpCode(), otpRegister.getEmail());
        return new MessageVerification("Vui lòng check mail OTP đã được gửi", 1);
    }

    private int otpCode(){
        Random random = new Random();
        int randomNumber = random.nextInt(9000) + 1000;
        return randomNumber;
    }
}
