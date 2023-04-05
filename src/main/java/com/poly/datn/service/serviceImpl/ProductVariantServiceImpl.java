package com.poly.datn.service.serviceImpl;

import com.poly.datn.common.mapper.ModelConverter;
import com.poly.datn.dto.request.ProductRequest;
import com.poly.datn.dto.request.ProductVariantRequest;
import com.poly.datn.dto.response.Pagination;
import com.poly.datn.dto.response.ProductResponse;
import com.poly.datn.dto.response.ProductVariantResponse;
import com.poly.datn.entity.ProductVariant;
import com.poly.datn.exception.cart.VariantUnavailable;
import com.poly.datn.repository.ProductRepository;
import com.poly.datn.repository.ProductVariantRepository;
import com.poly.datn.service.ProductVariantService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductVariantServiceImpl implements ProductVariantService {
    private final ModelConverter modelConverter;
    private final ProductVariantRepository productVariantRepository;
    @Override
    public Pagination<ProductVariantResponse> findByProductId(Pageable pageable, Integer id) {
        //Fix lỗi totalPage khi join bảng , tính thủ công totalPage
        Integer size = pageable.getPageSize();
        Integer totalPages= Math.ceil((float) productVariantRepository.findByProduct(id).stream().count()/size)==0
                ? 1: (int) Math.ceil((float)productVariantRepository.findByProduct(id).stream().count()/size);
       
        return new Pagination<ProductVariantResponse>(
                pageable.getPageSize(),
                pageable.getPageNumber(),
                totalPages,
                modelConverter.mapAllByIterator(productVariantRepository.findByProductId(id,pageable),ProductVariantResponse.class)
        );
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
    @Override
    public void delete(Integer id, Integer isDeleted) {
        productVariantRepository.delete(id,isDeleted);
    }

    @Override
    public ProductVariantResponse findById(Integer id) {

        ProductVariant variant = productVariantRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Variant with Id" + id + " not found"));
        if(!variant.getStatus()) throw new VariantUnavailable("Unavailable request");
        
        return modelConverter.map(variant, ProductVariantResponse.class);
    }
}
