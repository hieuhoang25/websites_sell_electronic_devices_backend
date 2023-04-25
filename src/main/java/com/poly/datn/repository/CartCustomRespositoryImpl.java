package com.poly.datn.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.poly.datn.entity.Cart;

public class CartCustomRespositoryImpl implements CartCustomRespository{

    @PersistenceContext
    private EntityManager em;
    @Override
    public void refresh(Cart cart) {
       em.refresh(cart);
    }
    
}
