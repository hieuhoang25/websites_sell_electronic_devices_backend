package com.poly.datn.service.serviceImpl;

import com.poly.datn.common.mapper.ModelConverter;
import com.poly.datn.dto.request.BrandRequest;
import com.poly.datn.dto.response.BrandResponse;
import com.poly.datn.dto.response.ListProductOfBrand;
import com.poly.datn.entity.Brand;
import com.poly.datn.repository.BrandRepository;
import com.poly.datn.service.CRUDBrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CRUDBrandServiceImpl implements CRUDBrandService {
    private final BrandRepository brandRepository;
    private final ModelConverter modelConverter;

    @Override
    public List<BrandResponse> findAll() {
        return modelConverter.mapAllByIterator(brandRepository.findAll(), BrandResponse.class);
    }

    @Override
    public BrandResponse createBranch(BrandRequest brandRequest) {
        Brand brand = modelConverter.map(brandRequest, Brand.class);
        return modelConverter.map(brandRepository.save(brand), BrandResponse.class);
    }

    @Override
    public BrandResponse updateById(BrandRequest brandRequest) {
        Brand branch = modelConverter.map(brandRequest, Brand.class);
        return modelConverter.map(brandRepository.save(branch), BrandResponse.class);
}

    @Override
    public ListProductOfBrand listProductOfBrand(Integer id) {
        Optional<Brand> list = brandRepository.findById(id);
        ListProductOfBrand listResponseResponse = modelConverter.map(list.get(), ListProductOfBrand.class);
        return listResponseResponse;
    }

    @Override
    public void delete(Integer id) {
        brandRepository.deleteById(id);
    }
}
