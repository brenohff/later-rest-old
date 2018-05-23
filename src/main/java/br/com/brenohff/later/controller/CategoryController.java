package br.com.brenohff.later.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.brenohff.later.models.LTCategory;
import br.com.brenohff.later.service.CategoryService;
import br.com.brenohff.later.service.UserService;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

	@Autowired
	CategoryService service;
	
	@Autowired
	UserService userService;

	@RequestMapping(value = "/saveCategory")
	private ResponseEntity<Void> saveCategory(@RequestBody LTCategory category) {
		service.saveCategories(category);
		return ResponseEntity.ok().build();
	}
	
	@RequestMapping(value = "/getAll")
	private ResponseEntity<List<LTCategory>> getAll(){
		List<LTCategory> ltCategory = service.getCategories();
		return ResponseEntity.status(HttpStatus.OK).body(ltCategory);
	}
	
}
