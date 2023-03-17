package com.poly.datn.service.serviceImpl;

import com.poly.datn.common.mapper.ModelConverter;
import com.poly.datn.dto.response.StorageResponse;
import com.poly.datn.repository.StorageRepository;
import com.poly.datn.service.CURDStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CURDStorageServiceImpl implements CURDStorageService {
    private final ModelConverter modelConverter;
    private final StorageRepository storageRepository;
    @Override
    public List<StorageResponse> findAll() {
        return modelConverter.mapAllByIterator(storageRepository.findAll(),StorageResponse.class);
    }
}
