package com.poly.datn.service.serviceImpl;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import com.poly.datn.common.mapper.ModelConverter;
import com.poly.datn.dto.request.InventoryRequest;
import com.poly.datn.dto.request.ProductDetailRequest;
import com.poly.datn.dto.response.InventoryResponse;
import com.poly.datn.dto.response.ProductDetailResponse;
import com.poly.datn.dto.response.ProductRatingResponse;
import com.poly.datn.entity.ProductVariant;
import com.poly.datn.entity.Rating;
import com.poly.datn.repository.ProductVariantRepository;
import com.poly.datn.repository.RatingRepository;
import com.poly.datn.service.ProductDetailService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class ProductDetailServiceImpl implements ProductDetailService {
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
                ratingRepository.findByProductId(productDetailRequest.getProductId())
                        .stream().sorted(Comparator.comparing(Rating::getCreatedDate,Comparator.reverseOrder()))
                        .collect(Collectors.toList()),
                ProductRatingResponse.class
        );
        productDetail.setRating(productRating);
        return productDetail;
    }

        @Override
        public InventoryResponse getInventory(InventoryRequest request) {  
                try {
                    Integer checked =  repository.checkInventoryById(request.getProduct_variant_id(), request.getRequest_quantity());
                
                    if(checked == 404) throw new IllegalArgumentException("Product variant not found");

                    ProductVariant variant =  repository.findById(request.getProduct_variant_id()).get();
                    Integer current = variant.getQuantity();

                    if(checked == 0) {
                        return new InventoryResponse(0, true);
                    }else if(checked <0) {
                        return new InventoryResponse( current, true );
                    }else if(checked == 202) {
                        return new InventoryResponse(current,true);
                    }
                    return new InventoryResponse(current, false);
                      
                }catch(Exception e) {
                         throw new IllegalArgumentException(e.getMessage());
                }
        }
}
