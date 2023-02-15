package com.poly.datn.service.serviceImpl;

import com.poly.datn.common.mapper.ModelConverter;
import com.poly.datn.dto.response.CategoryFindAllResponse;
import com.poly.datn.entity.Category;
import com.poly.datn.repository.CategoryRepository;
import com.poly.datn.service.CategoryFindAll;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryFindAllImpl implements CategoryFindAll {
    private final CategoryRepository categoryRepository;
    private final ModelConverter modelConverter;
    @Override
    public List<CategoryFindAllResponse> getCategory() {
        List<Category> categories = categoryRepository.findAll();
        return modelConverter.mapAllByIterator(categories,CategoryFindAllResponse.class);
    }
}
