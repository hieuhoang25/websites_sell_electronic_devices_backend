package com.poly.datn.service.serviceImpl;

import com.poly.datn.common.SearchCriteria;
import com.poly.datn.common.SearchResult;
import com.poly.datn.common.mapper.ModelConverter;
import com.poly.datn.dto.response.ProductFilterResponse;
import com.poly.datn.entity.Product;
import com.poly.datn.repository.ProductRepository;
import com.poly.datn.repository.specification.ProductSpecification;
import com.poly.datn.service.ProductFindByMultiField;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.Transient;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class ProductFindByMultiFieldImpl implements ProductFindByMultiField {

    private final ModelConverter modelConverter;

    private final ProductRepository productRepository;

    @Override
    public SearchResult<ProductFilterResponse>
    findByMultiField(List<SearchCriteria> criteria,
                     Pageable pageable,
                     String sortField,
                     String sortType) {
        ProductSpecification specification = new ProductSpecification();
        specification.setList(criteria);
        List<Product> products;
        Page<Product> filterResponses;
        if (sortField != null && sortType != null) {
            products = productRepository.findAll(specification);
            if (sortType.equalsIgnoreCase("asc") || sortType.equalsIgnoreCase("desc")) {
                Comparator<Product> comparator = Comparator.comparing(s -> {
                    try {
                        Field field = s.getClass().getDeclaredField(sortField);
                        if (field.getAnnotation(Transient.class) != null) {
                            Method getter = s.getClass().getMethod("get" + StringUtils.capitalize(sortField));
                            return (Comparable) getter.invoke(s);
                        }
                        field.setAccessible(true);
                        return (Comparable) field.get(s);
                    } catch (Exception e) {
                        throw new IllegalArgumentException("Invalid sort field " + e);
                    }
                });
                if (sortType.equalsIgnoreCase("desc"))
                    comparator = comparator.reversed();
                products = products.stream().sorted(comparator).collect(Collectors.toList());
            }
            PagedListHolder sort = new PagedListHolder(products);
            sort.setPageSize(pageable.getPageSize());
            sort.setPage(pageable.getPageNumber());
            return new SearchResult(
                    sort.getPageSize(),
                    sort.getPage(),
                    sort.getPageCount(),
                    modelConverter.mapAllByIterator(sort.getPageList(), ProductFilterResponse.class));
        }
        filterResponses = productRepository.findAll(specification, pageable);
        return new SearchResult(
                filterResponses.getSize(),
                filterResponses.getNumber(),
                filterResponses.getTotalPages(),
                modelConverter.mapAllByIterator(filterResponses.getContent(), ProductFilterResponse.class));
    }
}
