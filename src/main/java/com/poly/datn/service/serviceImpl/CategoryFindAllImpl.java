package com.poly.datn.service.serviceImpl;

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
    @Override
    public List<Category> findAll() {
        List<Category> categories = categoryRepository.findAll();
        for(Category c : categories.get(2).getCategories()){
            log.info(c.getCategoryName());
        }
        System.out.println("hello ??");
        return categories;
    }
}
