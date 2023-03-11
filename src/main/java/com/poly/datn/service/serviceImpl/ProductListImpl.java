package com.poly.datn.service.serviceImpl;

import com.poly.datn.common.SearchResult;
import com.poly.datn.common.mapper.ModelConverter;
import com.poly.datn.dto.response.ProductListResponse;
import com.poly.datn.entity.Product;
import com.poly.datn.repository.ProductRepository;
import com.poly.datn.service.ProductList;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(rollbackFor = Exception.class)
public class ProductListImpl implements ProductList {
    private final ProductRepository productRepository;
    private final ModelConverter modelConverter;

    @Override
    public SearchResult<ProductListResponse> getAllProducts(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);
        return new SearchResult(
                products.getSize(),
                products.getNumber(),
                products.getTotalPages(),
                modelConverter.mapAllByIterator(products.getContent(), ProductListResponse.class)
        );
    }
}
