package com.poly.datn.service.serviceImpl;

import com.poly.datn.common.mapper.ModelConverter;
import com.poly.datn.dto.response.ColorResponse;
import com.poly.datn.repository.ColorRepository;
import com.poly.datn.service.CURDColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CURDColorServiceImpl implements CURDColorService {
    private final ModelConverter modelConverter;
    private final ColorRepository colorRepository;
    @Override
    public List<ColorResponse> findAll() {
        return modelConverter.mapAllByIterator(colorRepository.findAll(),ColorResponse.class);
    }
}
