package com.poly.datn.service.serviceImpl;

import com.poly.datn.common.mapper.ModelConverter;
import com.poly.datn.dto.request.ProductAttributeRequest;
import com.poly.datn.dto.request.ProductVariantRequest;
import com.poly.datn.dto.response.ProductAttributeResponse;
import com.poly.datn.dto.response.ProductResponse;
import com.poly.datn.dto.response.ProductVariantResponse;
import com.poly.datn.entity.Product;
import com.poly.datn.entity.ProductAttribute;
import com.poly.datn.repository.ProductAttributeRepository;
import com.poly.datn.repository.ProductRepository;
import com.poly.datn.service.ProductAttributeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductAttributeServiceImpl implements ProductAttributeService {

    private  final ProductAttributeRepository productAttributeRepository;
    private  final ModelConverter modelConverter;
    @Override
    public ProductAttributeResponse create(ProductAttributeRequest productAttributeRequest) {
        ProductAttribute productAttribute = modelConverter.map(productAttributeRequest,ProductAttribute.class);
        return modelConverter.map(productAttributeRepository.save(productAttribute), ProductAttributeResponse.class);
    }

    @Override
    public List<ProductAttributeResponse> update(List<ProductAttributeRequest> productAttributeRequest) {
        List<ProductAttribute> productAttribute = modelConverter.mapAllByIterator(productAttributeRequest, ProductAttribute.class);
        return modelConverter.mapAllByIterator(productAttributeRepository.saveAll(productAttribute), ProductAttributeResponse.class);
    }

    @Override
    public List<ProductAttributeResponse> findByProductId(Integer id) {
        return modelConverter.mapAllByIterator(productAttributeRepository.findByProductId(id),ProductAttributeResponse.class);
    }

    @Override
    public List<ProductAttributeResponse> findAll() {
        return modelConverter.mapAllByIterator(productAttributeRepository.findAll(),ProductAttributeResponse.class);
    }

    @Override
    public void delete(List<Integer> id) {
        productAttributeRepository.deleteByListId(id);
    }


}
