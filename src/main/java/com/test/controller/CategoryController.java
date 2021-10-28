package com.test.controller;

import com.test.model.Category;
import com.test.service.category.ICategoryService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/categories")
public class CategoryController {

  @Autowired
  private ICategoryService categoryService;

  @GetMapping
  public ResponseEntity<Iterable<Category>> findCategory() {
    return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
  }

  @GetMapping("{id}")
  public ResponseEntity<Category> findById(@PathVariable Long id) {
    Optional<Category> category = categoryService.findById(id);
    if (category.isPresent()) {
      return new ResponseEntity<>(category.get(), HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

}
