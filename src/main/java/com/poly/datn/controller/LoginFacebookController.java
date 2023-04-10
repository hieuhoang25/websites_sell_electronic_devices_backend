
package com.poly.datn.controller;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.poly.datn.common.MessageResponse;
import com.poly.datn.common.ResponseBody;
import com.poly.datn.controller.router.Router;

import com.poly.datn.entity.User;
import com.poly.datn.repository.UserRepository;
import com.poly.datn.security.RefreshTokenService;
import com.poly.datn.security.jwt.JwtUtils;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RequestMapping(Router.API.BASE + Router.API.LOGIN_FACEBOOK)
@RestController
@RequiredArgsConstructor
@Tag(name = Router.API.BASE + Router.API.LOGIN_FACEBOOK)
public class LoginFacebookController {
          private final UserRepository userRepository;
          RestTemplate restTemplate = new RestTemplate();
          private final JwtUtils jwtUtils;
          private final RefreshTokenService refreshTokenService;

          @PostMapping
          public ResponseEntity<?> login(@RequestParam @Valid @NotBlank @NotNull String token,
          @RequestParam @Valid @NotBlank @NotNull String id)  {
                    String url = "https://graph.facebook.com/"+id+"?fields=id,email,name&access_token="+token;
                    restTemplate.getMessageConverters().add(0,
                                        new StringHttpMessageConverter(StandardCharsets.UTF_16LE));
                    String response = restTemplate.getForObject(url, String.class);
                    JSONObject jsonObject = new JSONObject(response);
                    String name = jsonObject.get("name").toString();
                    String email = jsonObject.get("email").toString();
                    User newUser = new User();
                    newUser.setEmail(email);
                    newUser.setFullName(name);
                    Optional<User> user = userRepository.findByEmail(email);
                    ResponseCookie jwtCookie = jwtUtils.generateJwtCookieFromName(name, Arrays.asList("USER"));
                    String refreshToken = jwtUtils.createRefreshToken(name);
                    
                 
                    if (user.isEmpty()) {                       
                              userRepository.save(newUser);    
                              ResponseCookie refreshTokenCookie = refreshTokenService.generateRefreshTokenCookie(newUser,
                              refreshToken);
                             
                              return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString(),
                                                  jwtCookie.toString()).build();
                    } else {
                      
                              if (user.get().getAccount()!= null) {
                                        return ResponseEntity.ok(new ResponseBody<>(
                                                            0,
                                                            MessageResponse.MESSAGE_ERROR,
                                                            "Email này đã được dùng để đăng ký tài khoản khác"));
                              }else{
                                        ResponseCookie refreshTokenCookie = refreshTokenService.generateRefreshTokenCookie(user.get(),
                                        refreshToken);
                                        refreshTokenService.deleteTokenByUserIdLimit(user.get().getId());
                                        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString(),
                                        jwtCookie.toString()).body(new ResponseBody<>(
                                                  1,
                                                  MessageResponse.MESSAGE_SUCCESS,
                                                  "Đăng nhập facebook thành công"));
                              }
                    }
      

          }


}
