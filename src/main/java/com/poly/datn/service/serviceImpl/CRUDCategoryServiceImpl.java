package com.poly.datn.service.serviceImpl;

import com.poly.datn.common.mapper.ModelConverter;
import com.poly.datn.dto.request.CategoryRequest;
import com.poly.datn.dto.response.CategoryResponse;
import com.poly.datn.entity.Category;
import com.poly.datn.repository.CategoryRepository;
import com.poly.datn.service.CRUDCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CRUDCategoryServiceImpl implements CRUDCategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelConverter modelConverter;

    @Override
    public List<CategoryResponse> findAll() {
        return modelConverter.mapAllByIterator(categoryRepository.findAll(), CategoryResponse.class);
    }

    @Override
    public CategoryResponse findById(Integer id) {
        return modelConverter.map(categoryRepository.findById(id).orElse(null), CategoryResponse.class);
    }
    public List<CategoryResponse> findAllParentAndChild() {
        return modelConverter.mapAllByIterator(categoryRepository.findAllParentAndChild(), CategoryResponse.class);
    }
    @Override
    public CategoryResponse create(CategoryRequest categoryRequest) {
        Category category = modelConverter.map(categoryRequest, Category.class);
        if (categoryRequest.getParent_id() != null) {
            category.setParent(categoryRepository.findById(categoryRequest.getParent_id()).orElse(null));
        }

        return modelConverter.map(categoryRepository.save(category), CategoryResponse.class);
    }

    @Override
    public CategoryResponse update(CategoryRequest categoryRequest) {
        Category category = modelConverter.map(categoryRequest, Category.class);
        if (categoryRequest.getParent_id() != null) {
            category.setParent(categoryRepository.findById(categoryRequest.getParent_id()).orElse(null));
        }

        return modelConverter.map(categoryRepository.save(category), CategoryResponse.class);
    }

    @Override
    public void delete(Integer id) {
        categoryRepository.deleteById(id);
    }
}
