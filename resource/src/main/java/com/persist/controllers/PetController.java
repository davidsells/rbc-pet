package com.persist.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.persist.model.Category;
import com.persist.model.CategoryRepository;
import com.persist.model.Pet;
import com.persist.model.PetRepository;

@RestController
public class PetController {
	private final PetRepository petRepository;
	private final CategoryRepository categoryRepository;

	@Autowired
	PetController(PetRepository petRepository, CategoryRepository categoryRepository) {
		this.petRepository = petRepository;
		this.categoryRepository = categoryRepository;
	}

	@RequestMapping(value = "/pet", method = RequestMethod.GET)
	Collection<Pet> getAllPets() {
		Collection<Category> categories = categoryRepository.findAll();
		System.out.println("What is the count: " + categories.size());
		return this.petRepository.findAll();
	}

	@RequestMapping(value = "/pet", method = RequestMethod.POST)
	Pet add(@RequestBody Pet input) {
		return this.petRepository.save(input);
	}

	@RequestMapping(value = "/pet/{id}", method = RequestMethod.DELETE)
	@ResponseBody void delete(@PathVariable Long id) {
		this.petRepository.delete(id);
	}
}
