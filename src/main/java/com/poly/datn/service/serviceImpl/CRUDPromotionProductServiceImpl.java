package com.poly.datn.service.serviceImpl;

import com.poly.datn.common.mapper.ModelConverter;
import com.poly.datn.dto.request.PromotionProductRequest;
import com.poly.datn.dto.response.PromotionProductResponse;
import com.poly.datn.entity.PromotionProduct;
import com.poly.datn.repository.PromotionProductRepository;
import com.poly.datn.service.CRUDPromotionProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CRUDPromotionProductServiceImpl implements CRUDPromotionProductService {
    private final PromotionProductRepository promotionProductRepository;
    private final ModelConverter modelConverter;
    @Override
    public PromotionProductResponse create(PromotionProductRequest promotionProductRequest) {
        PromotionProduct promotionProduct = modelConverter.map(promotionProductRequest,PromotionProduct.class );
        return modelConverter.map( promotionProductRepository.save(promotionProduct), PromotionProductResponse.class);
    }

    @Override
    public PromotionProductResponse update(PromotionProductRequest promotionProductRequest) {
        PromotionProduct promotionProduct = modelConverter.map(promotionProductRequest,PromotionProduct.class );
        return modelConverter.map( promotionProductRepository.save(promotionProduct), PromotionProductResponse.class);
    }

    @Override
    public void delete(Integer id) {
        promotionProductRepository.deleteById(id);
    }

    @Override
    public List<PromotionProductResponse> findAll() {
        return modelConverter.mapAllByIterator(promotionProductRepository.findAll(),PromotionProductResponse.class);
    }

    @Override
    public PromotionProductResponse findById(Integer id) {
        return modelConverter.map(promotionProductRepository.findById(id),PromotionProductResponse.class );
    }
}
