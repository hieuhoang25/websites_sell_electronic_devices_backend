package com.poly.datn.service.serviceImpl;

import com.poly.datn.common.mapper.ModelConverter;
import com.poly.datn.dto.response.ProductStorageResponse;
import com.poly.datn.entity.Storage;
import com.poly.datn.repository.StorageRepository;
import com.poly.datn.service.ProductStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class ProductStorageServiceImpl implements ProductStorageService {
    private final StorageRepository repository;
    private final ModelConverter converter;

    @Override
    public List<ProductStorageResponse> getProductStorage(Integer productId, Integer colorId) {
        List<Storage> storages = repository.findByColorAndProduct(colorId,productId);
        if(storages.isEmpty())
            throw new NotFoundException("storage not found");
        return converter.mapAllByIterator(storages, ProductStorageResponse.class);
    }

    @Override
    public List<ProductStorageResponse> getStorage() {
        List<Storage> storages = repository.findAll();
        return converter.mapAllByIterator(storages,ProductStorageResponse.class);
    }
}
