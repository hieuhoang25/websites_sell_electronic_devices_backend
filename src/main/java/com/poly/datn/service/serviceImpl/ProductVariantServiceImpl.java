package com.poly.datn.service.serviceImpl;

import com.poly.datn.common.mapper.ModelConverter;
import com.poly.datn.dto.request.ProductRequest;
import com.poly.datn.dto.request.ProductVariantRequest;
import com.poly.datn.dto.response.ProductResponse;
import com.poly.datn.dto.response.ProductVariantResponse;
import com.poly.datn.entity.ProductVariant;
import com.poly.datn.repository.ProductRepository;
import com.poly.datn.repository.ProductVariantRepository;
import com.poly.datn.service.ProductVariantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductVariantServiceImpl implements ProductVariantService {
    private final ModelConverter modelConverter;
    private final ProductVariantRepository productVariantRepository;
    @Override
    public List<ProductVariantResponse> findByProductId(Integer id) {
        return modelConverter.mapAllByIterator(productVariantRepository.findByProductId(id),ProductVariantResponse.class);
    }

    @Override
    public ProductVariantResponse  update(ProductVariantRequest productVariantRequest) {
        ProductVariant productVariant = modelConverter.map(productVariantRequest,ProductVariant.class);
        return modelConverter.map(productVariantRepository.save(productVariant),ProductVariantResponse.class);
    }

    @Override
    public ProductVariantResponse create(ProductVariantRequest productVariantRequest) {
        ProductVariant productVariant = modelConverter.map(productVariantRequest,ProductVariant.class);
        return modelConverter.map(productVariantRepository.save(productVariant),ProductVariantResponse.class);
    }
}
