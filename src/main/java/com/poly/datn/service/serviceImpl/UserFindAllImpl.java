package com.poly.datn.service.serviceImpl;

import com.poly.datn.common.SearchCriteria;
import com.poly.datn.common.SearchResult;
import com.poly.datn.common.mapper.ModelConverter;
import com.poly.datn.dto.response.ProductResponse;
import com.poly.datn.entity.Product;
import com.poly.datn.entity.User;
import com.poly.datn.repository.ProductRepository;
import com.poly.datn.repository.UserRepository;
import com.poly.datn.repository.specification.ProductSpecification;
import com.poly.datn.service.UserFindAll;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Service
public class UserFindAllImpl implements UserFindAll {
    private final UserRepository userRepository;

    private final ModelConverter modelConverter;

    private final ProductRepository productRepository;
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public SearchResult<ProductResponse> findProductsByMultiFiels(List<SearchCriteria> criteria, Pageable pageable) {
        ProductSpecification specification = new ProductSpecification();
        specification.setList(criteria);
         return new SearchResult<ProductResponse>(pageable.getPageSize(), pageable.getPageNumber(), modelConverter.mapAllByIterator(productRepository.findAll(specification,pageable).getContent(), ProductResponse.class));
    }

}
