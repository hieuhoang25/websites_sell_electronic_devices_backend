package com.poly.datn.service.serviceImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poly.datn.dto.request.CartDetailRequest;
import com.poly.datn.dto.response.CartDetailResponse;
import com.poly.datn.service.CartDetailService;

@Service
@Transactional
public class CartDetailImpl implements CartDetailService{

    @Override
    public CartDetailResponse getCartDetailById(Integer cartDetailId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCartDetailById'");
    }

    @Override
    public void update(CartDetailRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void add(CartDetailRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }

    @Override
    public void delete(CartDetailRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    
}
