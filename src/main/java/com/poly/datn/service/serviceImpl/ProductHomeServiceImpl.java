package com.poly.datn.service.serviceImpl;

import com.poly.datn.common.mapper.ModelConverter;
import com.poly.datn.dto.response.ProductFilterResponse;
import com.poly.datn.entity.Product;
import com.poly.datn.repository.ProductRepository;
import com.poly.datn.service.ProductHomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductHomeServiceImpl implements ProductHomeService {
    private final ProductRepository repository;
    private final ModelConverter converter;
    @Override
    public List<ProductFilterResponse> bigDiscount() {
        List<Product> products = repository.findByBigDiscount();
        List<ProductFilterResponse> productFilterResponses = new ArrayList<>();
        if(products.isEmpty())
            return  productFilterResponses;
        productFilterResponses = converter.mapAllByIterator(products, ProductFilterResponse.class);
        return productFilterResponses;
    }

    @Override
    public List<ProductFilterResponse> newArrival() {
        List<Product> products = repository.findByNewArrival();
        List<ProductFilterResponse> productFilterResponses = new ArrayList<>();
        if(products.isEmpty())
            return  productFilterResponses;
        productFilterResponses = converter.mapAllByIterator(products, ProductFilterResponse.class);
        return productFilterResponses;
    }
}
