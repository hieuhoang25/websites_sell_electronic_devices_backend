package com.poly.datn.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.poly.datn.entity.CartDetail;


// refresh for CartDetail
public interface CartDetailCustomRepository {
    public void refresh(CartDetail cartDetail);
}

