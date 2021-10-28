package com.test.controller;

import com.test.model.product.Product;
import com.test.model.product.ProductForm;
import com.test.service.product.IProductService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@CrossOrigin("*")
@RequestMapping("/products")
public class ProductController {

  @Autowired
  private IProductService productService;

  @GetMapping
  public ResponseEntity<Iterable<Product>> findAll() {
    Iterable<Product> products = productService.findAll();
    return new ResponseEntity<>(products, HttpStatus.OK);
  }

  @GetMapping("{id}")
  public ResponseEntity<Product> findById(@PathVariable Long id) {
    Optional<Product> product = productService.findById(id);
    if (product.isPresent()) {
      return new ResponseEntity<>(product.get(), HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @PostMapping
  public ResponseEntity<Product> save(ProductForm productForm) {
    MultipartFile file = productForm.getImage();
    String fileName = file.getOriginalFilename();
    Product product = new Product();
    product.setName(productForm.getName());
    product.setCategory(productForm.getCategory());
    product.setDescription(productForm.getDescription());
    product.setImage(fileName);
    productService.save(product);
    return new ResponseEntity<>(product, HttpStatus.OK);
  }

  @PutMapping("{id}")
  public ResponseEntity<Product> edit(@PathVariable("id") Long id, ProductForm productForm) {
    Optional<Product> product = productService.findById(id);
    if (product.isPresent()) {
      Product product1 = product.get();
      MultipartFile file = productForm.getImage();
      String fileName = file.getOriginalFilename();
      product1.setName(productForm.getName());
      product1.setCategory(productForm.getCategory());
      product1.setDescription(productForm.getDescription());
      product1.setImage(fileName);
      productService.save(product1);
      return new ResponseEntity<>(product1, HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<String> delete(@PathVariable Long id) {
    Optional<Product> product = productService.findById(id);
    if (product.isPresent()) {
      productService.delete(id);
      return new ResponseEntity<>("Done", HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

}
