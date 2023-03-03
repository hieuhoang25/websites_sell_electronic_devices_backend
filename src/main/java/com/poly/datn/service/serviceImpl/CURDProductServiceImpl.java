package com.poly.datn.service.serviceImpl;

import com.poly.datn.common.SearchResult;
import com.poly.datn.common.mapper.ModelConverter;
import com.poly.datn.dto.request.ProductRequest;
import com.poly.datn.dto.response.Pagination;
import com.poly.datn.dto.response.ProductResponse;
import com.poly.datn.entity.Product;
import com.poly.datn.repository.ProductRepository;
import com.poly.datn.service.CURDProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CURDProductServiceImpl implements CURDProductService {

    private final ProductRepository productRepository;
    private final ModelConverter modelConverter;



    @Override
    public Pagination<ProductResponse> findAll(Pageable pageable) {
        //Fix lỗi totalPage khi join bảng , tính thủ công totalPage
        Integer size = pageable.getPageSize();
        Integer totalPages= Math.ceil((float)productRepository.countProduct()/size)==0
                ? 1: (int) Math.ceil((float)productRepository.countProduct()/size);
        return new Pagination<ProductResponse>(
                pageable.getPageSize(),
                pageable.getPageNumber(),
                totalPages,
                modelConverter.mapAllByIterator(productRepository.findAllProductAndBrandName(pageable),ProductResponse.class)
        );

    }

    @Override
    public ProductResponse create(ProductRequest productRequest) {
        Product product = modelConverter.map(productRequest, Product.class);
        return modelConverter.map(productRepository.save(product),ProductResponse.class);
    }

    @Override
    public ProductResponse update(ProductRequest productRequest) {
        Product product = modelConverter.map(productRequest,Product.class);
        return modelConverter.map(productRepository.save(product),ProductResponse.class);

    }

    @Override
    public void delete(Integer id) {
        productRepository.deleteProduct(id);
    }
}
