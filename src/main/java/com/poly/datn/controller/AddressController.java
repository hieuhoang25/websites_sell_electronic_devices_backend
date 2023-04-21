package com.poly.datn.controller;

import static com.poly.datn.controller.router.Router.USER_API.ADDRESS;
import static com.poly.datn.controller.router.Router.USER_API.BASE;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.datn.dto.request.UserAddressRequest;
import com.poly.datn.dto.response.AddressResponse;
import com.poly.datn.service.UserAddressService;
import com.poly.datn.service.UserInfoByTokenService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(BASE + ADDRESS)
@RequiredArgsConstructor
@Slf4j
public class AddressController {

    private final UserInfoByTokenService userInfoService;
    private final UserAddressService addresssService;

    @GetMapping("/list")
    public ResponseEntity<?> getUserAddressList() {
        try {
            Integer userId = userInfoService.getCurrentUser().getId();
            List<AddressResponse> list = addresssService.findAllByUserId(userId);
            list.size();
            if (list.size() == 0)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No addresses found by current user");

            return ResponseEntity.ok().body(list);
        } catch (Exception e) {
            log.error("AddressController error " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERROR: " + e.getMessage());
        }
    }

    @GetMapping(value = "/default")
    public ResponseEntity<?> getDefaultAddressOfUser() {
        try {
            AddressResponse defaultAdd = addresssService.findDefaultAddress();
            return ResponseEntity.ok().body(defaultAdd);
        } catch (Exception e) {
            log.error("Error: " + e.getMessage());
            if (e instanceof EntityNotFoundException) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Default address not found");
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERROR: " + e.getMessage());
        }
    }

    @PostMapping("/default/{addressId}") 
    public ResponseEntity<?> setDefaultAddress(@PathVariable(required = true) Integer addressId) {
        try {
            Integer userId = userInfoService.getCurrentUser().getId();
            boolean result = addresssService.setDefaultAddress(addressId);
            if(!result) throw new RuntimeException("Can't set default address check log");

            return ResponseEntity.ok().body(addresssService.findAllByUserId(userId));
            
        }catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PostMapping
    public ResponseEntity<?> addNewAddress(@RequestBody UserAddressRequest request) {
        try {
            Integer userId = userInfoService.getCurrentUser().getId();
            AddressResponse response = addresssService.addAddress(request);
            return ResponseEntity.ok().body(addresssService.findAllByUserId(userId));
        } catch (Exception e) {
            log.error("Error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERROR: " + e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> updateAddress(@Valid @RequestBody UserAddressRequest request) {
        try {
            Integer userId = userInfoService.getCurrentUser().getId();
            AddressResponse response = addresssService.updateAddress(request);
            return ResponseEntity.ok().body(addresssService.findAllByUserId(userId));
        } catch (Exception e) {
            if (e instanceof RuntimeException) {
                return ResponseEntity.badRequest().body(e.getMessage());
            } else {
                log.error("Error: " + e.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERROR: " + e.getMessage());
            }
        }
    }

    @DeleteMapping
    public ResponseEntity<?> removeAddress(Integer id) {
        try {
            Integer userId = userInfoService.getCurrentUser().getId();
            addresssService.removeAddress(id);
            return ResponseEntity.ok().body(addresssService.findAllByUserId(userId));
        } catch (Exception e) {
            if (e instanceof RuntimeException) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERROR: ");
    }
}
