package com.car.areas.categories.services;

import com.car.areas.categories.models.bindingModels.CategoryCreateModel;
import com.car.areas.categories.models.viewModels.CategoryViewModel;

import java.util.List;

/**
 * 20/04/2017
 */
public interface CategoryService {

    CategoryViewModel getCategoryById(long id);

    CategoryViewModel create(CategoryCreateModel categoryCreateModel);

    List<CategoryViewModel> gatAll();

    void delete(long categoryId);
}
