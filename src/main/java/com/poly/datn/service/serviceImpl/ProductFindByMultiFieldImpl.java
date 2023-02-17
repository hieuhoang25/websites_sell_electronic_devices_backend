package com.poly.datn.service.serviceImpl;

import com.poly.datn.common.SearchCriteria;
import com.poly.datn.common.SearchResult;
import com.poly.datn.common.mapper.ModelConverter;
import com.poly.datn.dto.response.ProductFilterResponse;
import com.poly.datn.repository.ProductRepository;
import com.poly.datn.repository.specification.ProductSpecification;
import com.poly.datn.service.ProductFindByMultiField;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductFindByMultiFieldImpl implements ProductFindByMultiField {

    private final ModelConverter modelConverter;

    private final ProductRepository productRepository;

    @Override
    public SearchResult<ProductFilterResponse> findByMultiField(List<SearchCriteria> criteria, Pageable pageable) {
        ProductSpecification specification = new ProductSpecification();
        specification.setList(criteria);
        return new SearchResult<ProductFilterResponse>(
                pageable.getPageSize(),
                pageable.getPageNumber(),
                modelConverter.mapAllByIterator(productRepository.findAll(specification, pageable).getContent(),
                        ProductFilterResponse.class));
    }
}
