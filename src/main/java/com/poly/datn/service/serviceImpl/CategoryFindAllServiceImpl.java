package com.poly.datn.service.serviceImpl;

import com.poly.datn.common.mapper.ModelConverter;
import com.poly.datn.dto.response.CategoryFindAllResponse;
import com.poly.datn.entity.Category;
import com.poly.datn.repository.CategoryRepository;
import com.poly.datn.service.CategoryFindAllService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class CategoryFindAllServiceImpl implements CategoryFindAllService {
    private final CategoryRepository categoryRepository;
    private final ModelConverter modelConverter;
    @Override
    public List<CategoryFindAllResponse> getCategory() {
        List<Category> categories = categoryRepository.findAll();
        return modelConverter.mapAllByIterator(categories,CategoryFindAllResponse.class);
    }
}
