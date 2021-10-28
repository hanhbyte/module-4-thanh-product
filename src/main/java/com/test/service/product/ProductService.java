package com.test.service.product;

import com.test.model.product.Product;
import com.test.repository.IProductRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService{

  @Autowired
  private IProductRepository productRepository;

  @Override
  public Iterable<Product> findAll() {
    return productRepository.findAll();
  }

  @Override
  public Product save(Product product) {
    return productRepository.save(product);
  }

  @Override
  public void delete(Long id) {
      productRepository.deleteById(id);
  }

  @Override
  public Optional<Product> findById(Long id) {
    return productRepository.findById(id);
  }
}
