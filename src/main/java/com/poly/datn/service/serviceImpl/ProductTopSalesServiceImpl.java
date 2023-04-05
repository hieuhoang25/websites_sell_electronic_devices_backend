package com.poly.datn.service.serviceImpl;

import com.poly.datn.common.mapper.ModelConverter;
import com.poly.datn.dto.response.ProductFilterResponse;
import com.poly.datn.entity.Product;
import com.poly.datn.repository.ProductRepository;
import com.poly.datn.service.ProductTopSalesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductTopSalesServiceImpl implements ProductTopSalesService {
    private final ProductRepository repository;
    private final ModelConverter converter;
    @Override
    public List<ProductFilterResponse> topSales() {
        List<Product> products = repository.findByTopSales();
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
