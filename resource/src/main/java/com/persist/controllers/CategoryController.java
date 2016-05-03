package com.persist.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.persist.model.Category;
import com.persist.model.CategoryRepository;

@RestController
public class CategoryController {
        private final CategoryRepository categoryRepository;

        @Autowired
        public CategoryController(CategoryRepository categoryRepository) {
                super();
                this.categoryRepository = categoryRepository;
        }

        @RequestMapping(value = "/category", method = RequestMethod.GET)
        Collection<Category> getAllCategories() {
                return this.categoryRepository.findAll();
        }

        @RequestMapping(value = "/category", method = RequestMethod.POST)
        Category add(@RequestBody Category input) {
                return this.categoryRepository.save(input);
        }
}