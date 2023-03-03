package com.poly.datn.service.serviceImpl;

import com.poly.datn.common.mapper.ModelConverter;
import com.poly.datn.dto.response.ProductColorResponse;
import com.poly.datn.entity.Color;
import com.poly.datn.repository.ColorRepository;
import com.poly.datn.service.ProductColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class ProductColorServiceImpl implements ProductColorService {
    private final ColorRepository repository;
    private final ModelConverter converter;

    @Override
    public List<ProductColorResponse> getProductColor(Integer productId) {
        List<Color> productColors = repository.findByProduct(productId);
        if (productColors.isEmpty())
            throw new NotFoundException("Color find by product not found");
        return converter.mapAllByIterator(productColors, ProductColorResponse.class);
    }
}
