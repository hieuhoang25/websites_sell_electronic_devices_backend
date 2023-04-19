package com.poly.datn.service.serviceImpl;

import com.poly.datn.common.mapper.ModelConverter;
import com.poly.datn.dto.response.Pagination;
import com.poly.datn.dto.response.ProductFilterResponse;
import com.poly.datn.repository.ProductRepository;
import com.poly.datn.service.ProductHomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductHomeServiceImpl implements ProductHomeService {
    private final ProductRepository repository;
    private final ModelConverter converter;

    @Override
    public Pagination bigDiscount(Pageable pageable) {
        LocalDateTime now = LocalDateTime.now();
        ZoneId zoneId = ZoneId.systemDefault();
        PagedListHolder productPage = new PagedListHolder(repository.findByBigDiscount(now.atZone(zoneId).toInstant()));
        productPage.setPageSize(pageable.getPageSize());
        productPage.setPage(pageable.getPageNumber());
        List<ProductFilterResponse> productFilterResponses =
                converter.mapAllByIterator(productPage.getPageList(), ProductFilterResponse.class);
        return new Pagination(
                productPage.getPageSize(),
                productPage.getPage(),
                productPage.getPageCount(),
                productFilterResponses
        );
    }

    @Override
    public Pagination newArrival(Pageable pageable) {
        PagedListHolder productPage = new PagedListHolder(repository.findByNewArrival());
        productPage.setPageSize(pageable.getPageSize());
        productPage.setPage(pageable.getPageNumber());
        List<ProductFilterResponse> productFilterResponses =
                converter.mapAllByIterator(productPage.getPageList(), ProductFilterResponse.class);
        return new Pagination(
                productPage.getPageSize(),
                productPage.getPage(),
                productPage.getPageCount(),
                productFilterResponses
        );
    }

    @Override
    public Pagination topSales(Pageable pageable) {
        PagedListHolder productPage = new PagedListHolder(repository.findByTopSales());
        productPage.setPageSize(pageable.getPageSize());
        productPage.setPage(pageable.getPageNumber());
        List<ProductFilterResponse> productFilterResponses =
                converter.mapAllByIterator(productPage.getPageList(), ProductFilterResponse.class);
        return new Pagination(
                productPage.getPageSize(),
                productPage.getPage(),
                productPage.getPageCount(),
                productFilterResponses
        );
    }
}
