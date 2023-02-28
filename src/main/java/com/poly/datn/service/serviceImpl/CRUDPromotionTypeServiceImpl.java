package com.poly.datn.service.serviceImpl;

import com.poly.datn.common.mapper.ModelConverter;
import com.poly.datn.dto.request.PromotionTypeRequest;
import com.poly.datn.dto.response.PromotionTypeResponse;
import com.poly.datn.entity.PromotionType;
import com.poly.datn.repository.PromotionTypeRepository;
import com.poly.datn.service.CRUDPromotionTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CRUDPromotionTypeServiceImpl implements CRUDPromotionTypeService {

    private final PromotionTypeRepository promotionTypeRepository;
    private final ModelConverter modelConverter;
    @Override
    public PromotionTypeResponse create(PromotionTypeRequest promotionTypeRequest) {
        PromotionType promotionType = modelConverter.map(promotionTypeRequest, PromotionType.class);
        return modelConverter.map(promotionTypeRepository.save(promotionType), PromotionTypeResponse.class);
    }

    @Override
    public PromotionTypeResponse update(PromotionTypeRequest promotionTypeRequest) {
        PromotionType promotionType = modelConverter.map(promotionTypeRequest, PromotionType.class);
        return modelConverter.map(promotionTypeRepository.save(promotionType), PromotionTypeResponse.class);
    }

    @Override
    public void delete(Integer id) {
        promotionTypeRepository.deleteById(id);
    }

    @Override
    public List<PromotionTypeResponse> findAll() {
        return modelConverter.mapAllByIterator(promotionTypeRepository.findAll(),PromotionTypeResponse.class);
    }

    @Override
    public PromotionTypeResponse findById(Integer id) {
        return modelConverter.map(promotionTypeRepository.findById(id), PromotionTypeResponse.class);
    }
}
