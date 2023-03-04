package com.poly.datn.service.serviceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.poly.datn.common.mapper.ModelConverter;
import com.poly.datn.dto.request.CartDetailRequest;
import com.poly.datn.dto.response.CartResponse;
import com.poly.datn.entity.Cart;
import com.poly.datn.entity.CartDetail;
import com.poly.datn.repository.CartDetailRepository;
import com.poly.datn.repository.CartRepository;
import com.poly.datn.service.CartService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepo;
    private final ModelConverter modelConverter;
    private final CartDetailRepository cartDetail;

    @Override
    public CartResponse findByUserId(Integer userId) {
        CartResponse cart = null;
        try {
            Cart c = cartRepo.findCartByUserId(userId);
            log.info("cart: " + c);
           return  modelConverter.map(c , CartResponse.class); 
        }catch(Exception e) {
           System.out.println("ex impl");
            e.printStackTrace();
        }
      return cart;
        // throw new UnsupportedOperationException("Unimplemented method 'findByUserId'");
    }



    @Override
    public CartResponse updateCart(List<CartDetailRequest> items) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateCart'");
    }

    @Override
    public boolean exitsProductVariantInCart(Integer variantId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'exitsProductVariantInCart'");
    }



    @Override
    public CartResponse addProductToCart(List<CartDetailRequest> items) {
        
        try {
            Set<CartDetail> cartDetails =  new HashSet<>(modelConverter.mapAllByIterator(items, CartDetail.class));
        }catch()
      
        // cartDetail.saveAll(items);
        return new CartResponse();
    }


    
}
