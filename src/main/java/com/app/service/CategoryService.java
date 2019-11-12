package com.app.service;

import com.app.Exception.MyException;
import com.app.dto.CategoryDto;
import com.app.model.Category;
import com.app.modelMapper.MyModelMapper;
import com.app.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryDto add(CategoryDto categoryDto) {
        if (categoryDto == null) {
            throw new MyException("Category shouldn't be null");
        }
        Category category = MyModelMapper.fromCategoryDtoToCategory(categoryDto);
        Category category1 = categoryRepository.save(category);
        return MyModelMapper.fromCategoryToCategoryDto(category1);
    }

    public CategoryDto findById(Long id) {
        if (id == null) {
            throw new MyException("id shouldn't be null");
        }
        return categoryRepository
                .findById(id)
                .map(MyModelMapper::fromCategoryToCategoryDto)
                .orElseThrow(() -> new MyException("There is no category with this id"));
    }

    public List<CategoryDto> findAll() {
        return categoryRepository.findAll()
                .stream()
                .map(MyModelMapper::fromCategoryToCategoryDto)
                .collect(Collectors.toList());
    }

    public CategoryDto deleteCategory(Long id) {
        if (id == null) {
            throw new MyException("id shouldn't be null");
        }
        CategoryDto categoryDto = categoryRepository.findById(id)
                .map(MyModelMapper::fromCategoryToCategoryDto)
                .orElseThrow(() -> new MyException("there is no category with this id"));
        categoryRepository.deleteById(id);
        return categoryDto;
    }

    public CategoryDto updateCategory(Long id, CategoryDto categoryDto) {
        if (id == null) {
            throw new MyException("id shouldn't be null");
        }
        if (categoryDto == null) {
            throw new MyException("category with update data shouldn't be null");
        }

        Category categoryFromDb = categoryRepository.findById(id)
                .orElseThrow(() -> new MyException("There is no category with this id"));

        categoryFromDb.setName(categoryDto.getName() == null ? categoryFromDb.getName() : categoryDto.getName());
        return MyModelMapper.fromCategoryToCategoryDto(categoryRepository.save(categoryFromDb));
    }

}
