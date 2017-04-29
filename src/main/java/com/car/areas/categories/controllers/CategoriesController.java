package com.car.areas.categories.controllers;

/**
 * 20/04/2017
 */

import com.car.areas.categories.models.bindingModels.CategoryCreateModel;
import com.car.areas.categories.models.viewModels.CategoryViewModel;
import com.car.areas.categories.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoriesController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<List<CategoryViewModel>> getCategories() {
        List<CategoryViewModel> categoryViewModels = this.categoryService.gatAll();

        return new ResponseEntity<>(categoryViewModels, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<CategoryViewModel> create(@RequestParam(name = "category") String categoryName) {
        CategoryCreateModel categoryCreateModel = new CategoryCreateModel();
        categoryCreateModel.setName(categoryName);
        CategoryViewModel categoryViewModel = this.categoryService.create(categoryCreateModel);

        return new ResponseEntity<>(categoryViewModel, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") long categoryId) {
        this.categoryService.delete(categoryId);

        return new ResponseEntity(HttpStatus.OK);
    }
}
