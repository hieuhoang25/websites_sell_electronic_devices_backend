package com.poly.datn.service.serviceImpl;

import com.poly.datn.common.mapper.ModelConverter;
import com.poly.datn.dto.request.ProductDetailRequest;
import com.poly.datn.dto.response.ProductDetailResponse;
import com.poly.datn.entity.ProductVariant;
import com.poly.datn.repository.ProductVariantRepository;
import com.poly.datn.service.ProductDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProductDetailImpl implements ProductDetail {
    private final ProductVariantRepository repository;
    private final ModelConverter modelConverter;

    @Override
    public ProductDetailResponse getProductDetail(ProductDetailRequest productDetailRequest) {
        ProductVariant productVariant = repository.
                findByProductAndColor(
                        productDetailRequest.getColorId(),
                        productDetailRequest.getProductId(),
                        productDetailRequest.getStorageId()
                );
        if (Objects.isNull(productVariant))
            throw new NotFoundException("ProductVariant not found!");
        return modelConverter.map(productVariant, ProductDetailResponse.class);
    }
}
