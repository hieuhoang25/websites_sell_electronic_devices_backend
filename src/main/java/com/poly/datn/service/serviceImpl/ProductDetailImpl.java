package com.poly.datn.service.serviceImpl;

import com.poly.datn.common.mapper.ModelConverter;
import com.poly.datn.dto.request.ProductDetailRequest;
import com.poly.datn.dto.response.ProductDetailResponse;
import com.poly.datn.dto.response.ProductRatingResponse;
import com.poly.datn.entity.ProductVariant;
import com.poly.datn.repository.ProductVariantRepository;
import com.poly.datn.repository.RatingRepository;
import com.poly.datn.service.ProductDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class ProductDetailImpl implements ProductDetail {
    private final ProductVariantRepository repository;
    private final ModelConverter modelConverter;
    private final RatingRepository ratingRepository;

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
        ProductDetailResponse productDetail = modelConverter.map(productVariant, ProductDetailResponse.class);
        //get list rating of product
        List<ProductRatingResponse> productRating = modelConverter.mapAllByIterator(
                ratingRepository.findByProductId(productDetailRequest.getProductId()),
                ProductRatingResponse.class
        );
        productDetail.setRating(productRating);
        return productDetail;
    }
}
