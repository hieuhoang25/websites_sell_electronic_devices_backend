package com.poly.datn.service;

import java.util.List;

import com.poly.datn.dto.request.UserAddressRequest;
import com.poly.datn.dto.response.AddressResponse;

public interface UserAddressService {

    List<AddressResponse> findAllByUserId(Integer userId);

    AddressResponse findById(Integer id);

    AddressResponse findDefaultAddress();

    AddressResponse updateAddress(UserAddressRequest request);

    boolean removeAddress(Integer request);
    
    AddressResponse addAddress(UserAddressRequest request);

    boolean setDefaultAddress(Integer addressId);

    
}
