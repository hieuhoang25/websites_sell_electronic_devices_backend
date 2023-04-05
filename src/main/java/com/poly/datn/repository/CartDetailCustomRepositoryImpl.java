package com.poly.datn.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.poly.datn.entity.CartDetail;

public class CartDetailCustomRepositoryImpl implements CartDetailCustomRepository {
    @PersistenceContext
   private EntityManager em;


   @Transactional
   @Override
   public void refresh(CartDetail parkrunCourse) {
      em.refresh(parkrunCourse);
   }
}
