package br.com.brenohff.later.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.brenohff.later.models.LTCategory;
import br.com.brenohff.later.service.CategoryService;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

	@Autowired
	CategoryService service;

	@RequestMapping(value = "/save")
	private ResponseEntity<Void> saveCategory() {
		return ResponseEntity.ok().build();
	}

	@RequestMapping(value = "/initiate")
	private void initiateCategories() {
		List<LTCategory> ltCategory = new ArrayList<>();

		for (LTCategory category : ltCategory) {
			service.saveCategories(category);
		}
	}
	
	@RequestMapping(value = "/getAll")
	private ResponseEntity<List<LTCategory>> getAll(){
		List<LTCategory> ltCategory = service.getCategories();
		return ResponseEntity.status(HttpStatus.OK).body(ltCategory);
	}

}
