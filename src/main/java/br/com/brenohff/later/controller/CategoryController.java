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
import br.com.brenohff.later.models.LTUser;
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
	
	
	// TODO APAGAR ESTE MÉTODO
	@RequestMapping(value = "/initiate")
	private void initiateCategories() {
		
		LTUser user = new LTUser();
		user.setId("1369288233173370");
		user.setEmail("breno15555@gmail.com");
		user.setName("Breno Henrique");
		user.setBirthday("05/22/1996");
		user.setGender("male");
		user.setLink("https://www.facebook.com/app_scoped_user_id/YXNpZADpBWEd5azRYdHpjWThNSFB0di1nN1NlX2NVRWlqcUh4Snd1TGxFZA2hZATFFIMFBJbkZAkRkpETFVuaFFDTGZAqZAjFnSnlqS0cwVnY2THlLN0JERkllUlhfZAFlZAaU5zcU04UVdoUnNvY0UyT3BVRjhpNFpa/");
		user.setImage("https://graph.facebook.com/1369288233173370/picture");
		user.setImage_long("https://graph.facebook.com/1369288233173370/picture?type=large");
		
		userService.saveUser(user);
		
		
		List<LTCategory> ltCategory = new ArrayList<>();
		ltCategory.add(new LTCategory("https://firebasestorage.googleapis.com/v0/b/later-6c492.appspot.com/o/categories_images%2Faxe_category.jpg?alt=media&token=190ce909-6886-4610-aa8e-43b5526b5ad1", "Axé", "#FFC107", "#FFA000"));
		ltCategory.add(new LTCategory("https://firebasestorage.googleapis.com/v0/b/later-6c492.appspot.com/o/categories_images%2Feletronico_category.jpg?alt=media&token=b2e53f31-9e88-4d11-b289-9cd473f11923", "Eletrônico", "#9C27B0", "#7B1FA2"));
		ltCategory.add(new LTCategory("https://firebasestorage.googleapis.com/v0/b/later-6c492.appspot.com/o/categories_images%2Fforro_category.jpg?alt=media&token=45fb0366-a2ed-44b1-aa53-f579bfeec64f", "Forró", "#795548", "#5D4037"));
		ltCategory.add(new LTCategory("https://firebasestorage.googleapis.com/v0/b/later-6c492.appspot.com/o/categories_images%2Ffunk_category.jpg?alt=media&token=f5765589-b9fd-4ff2-b92f-915cc524d8c0", "Funk", "#E91E63", "#C2185B"));
		ltCategory.add(new LTCategory("https://firebasestorage.googleapis.com/v0/b/later-6c492.appspot.com/o/categories_images%2Fgospel_category.jpg?alt=media&token=74f00c71-bbcc-4cd1-9c50-03c7c6ede5a7", "Gospel", "#CDDC39", "#AFB42B"));
		ltCategory.add(new LTCategory("https://firebasestorage.googleapis.com/v0/b/later-6c492.appspot.com/o/categories_images%2Fhouse_category.jpg?alt=media&token=4e3e88bf-766f-4481-a86c-09e6b2dce615", "House", "#00BCD4", "#0097A7"));
		ltCategory.add(new LTCategory("https://firebasestorage.googleapis.com/v0/b/later-6c492.appspot.com/o/categories_images%2Fpop_category.jpg?alt=media&token=6c002507-11ba-4a04-ac7f-1ad2f35f8fbf", "Pop", "#2196F3", "#1976D2"));
		ltCategory.add(new LTCategory("https://firebasestorage.googleapis.com/v0/b/later-6c492.appspot.com/o/categories_images%2Frock_category.jpg?alt=media&token=38b01c4e-a8bd-434e-aabe-aa8a9ac80166", "Rock", "#F44336", "#D32F2F"));
		ltCategory.add(new LTCategory("https://firebasestorage.googleapis.com/v0/b/later-6c492.appspot.com/o/categories_images%2Fsertanejo_category.jpg?alt=media&token=b9eb9162-b811-49ea-82c4-74b497500528","Sertanejo","#FF9800", "#F57C00"));
		
		for (LTCategory category : ltCategory) {
			service.saveCategories(category);
		}
	}
	
}
