package com.poly.datn.service.serviceImpl;

import com.poly.datn.common.mapper.ModelConverter;
import com.poly.datn.dto.request.UserInfoRequest;
import com.poly.datn.dto.response.UserLoggedResponse;
import com.poly.datn.entity.Account;
import com.poly.datn.entity.Address;
import com.poly.datn.entity.User;
import com.poly.datn.exception.BadRequestException;
import com.poly.datn.repository.AccountRepository;
import com.poly.datn.repository.AddressRepository;
import com.poly.datn.repository.UserRepository;
import com.poly.datn.service.UserInfoByTokenService;
import com.poly.datn.utils.MailUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserInfoByTokenServiceImpl implements UserInfoByTokenService {
    private final AccountRepository accountRepository;
    private final ModelConverter converter;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    @Override
    public UserLoggedResponse getUserInfo() {
        UserLoggedResponse response = converter.map(getCurrentUser(), UserLoggedResponse.class);
        return response;
    }

    @Override
    public User getCurrentUser() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        if (MailUtil.validateEmail(name)) {
            User user = userRepository.findByEmail(name).get();
            if (user == null)
                throw new NotFoundException("user not found");
            return user;
        }
        Account account = accountRepository.findByUsername(name);
        if (account == null)
            throw new NotFoundException("user not found");
        return account.getUser();
    }

    @Override
    public UserLoggedResponse updateUserInformation(UserInfoRequest request) {
        User user = getCurrentUser();
        // exception
//        assert user !=null : "User not found";
        user.setPhone(request.getPhone());
        user.setFullName(request.getFull_name());
//        log.info("{}", isGoogleEmail(request.getEmail()));
        if (isGoogleEmail(request.getEmail())) {
            User existsEmail = userRepository.findByEmail(request.getEmail()).orElse(null);
            if (existsEmail == null) user.setEmail(request.getEmail());
            else {
                if (!existsEmail.getId().equals(user.getId())) {
                    throw new RuntimeException(String.format("Email %s đã được sử dụng", existsEmail.getEmail()));
                }
            }
        }
        userRepository.save(user);
        UserLoggedResponse response = converter.map(user, UserLoggedResponse.class);
        return response;
    }

    public boolean isGoogleEmail(String email) {
        boolean isGoogleEmail = false;
        try {
            InternetAddress internetAddress = new InternetAddress(email);
            String address = internetAddress.getAddress().toLowerCase();
            String domain = address.substring(address.lastIndexOf("@") + 1);
            if (domain.equals("gmail.com") || domain.equals("googlemail.com")) {
                isGoogleEmail = true;
            }
        } catch (AddressException e) {
            // Invalid email address
            throw new RuntimeException("The email " + email + "không có trên tồn tại");
        }
        return isGoogleEmail;
    }

}
