package br.com.brenohff.later.api;

import br.com.brenohff.later.model.LTCategory;
import br.com.brenohff.later.repository.CategoryRepository;
import br.com.brenohff.later.service.CategoryService;
import br.com.brenohff.later.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/categories")
@CrossOrigin
public class CategoryController {

    @Autowired
    CategoryService service;

    @Autowired
    CategoryRepository repository;

    @Autowired
    UserService userService;

    @ApiOperation(value = "Busca todas as categorias.")
    @GetMapping(value = "/getAll")
    private ResponseEntity<List<LTCategory>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getCategories());
    }

    //TODO Remover depois que todas as categorias já estiverem sido inseridas.
    @GetMapping(value = "/includeCategories")
    private ResponseEntity<Void> includeCategories() {

        List<LTCategory> ltCategory = new ArrayList<>();

        ltCategory.add(new LTCategory("https://s3.amazonaws.com/later-s3/0D9ZxmQQta.jpg", "Axé", "#FFC107", "#FFA000"));
        ltCategory.add(new LTCategory("https://s3.amazonaws.com/later-s3/0D9ZxmQQta.jpg", "Eletrônico", "#9C27B0", "#7B1FA2"));
        ltCategory.add(new LTCategory("https://s3.amazonaws.com/later-s3/0D9ZxmQQta.jpg", "Forró", "#795548", "#5D4037"));
        ltCategory.add(new LTCategory("https://s3.amazonaws.com/later-s3/0D9ZxmQQta.jpg", "Funk", "#E91E63", "#C2185B"));
        ltCategory.add(new LTCategory("https://s3.amazonaws.com/later-s3/0D9ZxmQQta.jpg", "Gospel", "#CDDC39", "#AFB42B"));
        ltCategory.add(new LTCategory("https://s3.amazonaws.com/later-s3/0D9ZxmQQta.jpg", "House", "#00BCD4", "#0097A7"));
        ltCategory.add(new LTCategory("https://s3.amazonaws.com/later-s3/0D9ZxmQQta.jpg", "Pop", "#2196F3", "#1976D2"));
        ltCategory.add(new LTCategory("https://s3.amazonaws.com/later-s3/0D9ZxmQQta.jpg", "Rock", "#F44336", "#D32F2F"));

        repository.save(ltCategory);

        return ResponseEntity.ok().build();
    }

}
