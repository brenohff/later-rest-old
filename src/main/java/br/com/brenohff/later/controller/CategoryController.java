package br.com.brenohff.later.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.brenohff.later.models.LTCategory;
import br.com.brenohff.later.models.LTEvent;
import br.com.brenohff.later.service.CategoryService;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

	@Autowired
	CategoryService service;

	@RequestMapping(value = "/saveCategory")
	private ResponseEntity<Void> saveCategory(@RequestBody LTCategory category) {
		service.saveCategories(category);
		return ResponseEntity.ok().build();
	}

	@RequestMapping(value = "/initiate")
	private void initiateCategories() {
		
		List<LTCategory> ltCategory = new ArrayList<>();
		ltCategory.add(new LTCategory("https://firebasestorage.googleapis.com/v0/b/later-6c492.appspot.com/o/categories_images%2Faxe_category.jpg?alt=media&token=190ce909-6886-4610-aa8e-43b5526b5ad1", "Axé", ""));
		ltCategory.add(new LTCategory("https://firebasestorage.googleapis.com/v0/b/later-6c492.appspot.com/o/categories_images%2Feletronico_category.jpg?alt=media&token=b2e53f31-9e88-4d11-b289-9cd473f11923", "Eletrônico", ""));

		for (LTCategory category : ltCategory) {
			service.saveCategories(category);
		}
	}
	
	@RequestMapping(value = "/getAll")
	private ResponseEntity<List<LTCategory>> getAll(){
		List<LTCategory> ltCategory = service.getCategories();
		return ResponseEntity.status(HttpStatus.OK).body(ltCategory);
	}

	/**
	  
	  	private String url;
		private String name;
		private String baseColor;
		<!-- CORES CATEGORIAS -->
	    <color name="pop">#2196F3</color>
	    <color name="pop_700">#1976D2</color>
	
	    <color name="funk">#E91E63</color>
	    <color name="funk_700">#C2185B</color>
	
	    <color name="house">#00BCD4</color>
	    <color name="house_700">#0097A7</color>
	
	    <color name="eletronico">#9C27B0</color>
	    <color name="eletronico_700">#7B1FA2</color>
	
	    <color name="rock">#F44336</color>
	    <color name="rock_700">#D32F2F</color>
	
	    <color name="axe">#FFC107</color>
	    <color name="axe_700">#FFA000</color>
	
	    <color name="sertanejo">#FF9800</color>
	    <color name="sertanejo_700">#F57C00</color>
	
	    <color name="gospel">#CDDC39</color>
	    <color name="gospel_700">#AFB42B</color>
	
	    <color name="forro">#795548</color>
	    <color name="forro_700">#5D4037</color> 
	 */
	
}
