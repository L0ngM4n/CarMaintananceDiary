package com.car.areas.categories.servicesImpl;

import com.car.areas.categories.entities.Category;
import com.car.areas.categories.models.bindingModels.CategoryCreateModel;
import com.car.areas.categories.models.viewModels.CategoryViewModel;
import com.car.areas.categories.repositories.CategoryRepository;
import com.car.areas.categories.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 20/04/2017
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryViewModel getCategoryById(long id) {

        Category category = this.categoryRepository.findOne(id);
        CategoryViewModel categoryViewModel = this.modelMapper.map(category, CategoryViewModel.class);

        return categoryViewModel;
    }

    @Override
    public CategoryViewModel create(CategoryCreateModel categoryCreateModel) {
        Category category = this.modelMapper.map(categoryCreateModel, Category.class);

        category = this.categoryRepository.save(category);
        CategoryViewModel categoryViewModel = this.modelMapper.map(category, CategoryViewModel.class);

        return categoryViewModel;
    }

    @Override
    public List<CategoryViewModel> gatAll() {
        Iterable<Category> categories = this.categoryRepository.findAll();
        List<CategoryViewModel> categoryViewModels = new ArrayList<>();

        for (Category category : categories) {
            categoryViewModels.add(this.modelMapper.map(category, CategoryViewModel.class));
        }

        return categoryViewModels;
    }

    @Override
    public void delete(long categoryId) {

        this.categoryRepository.delete(categoryId);
    }
}
