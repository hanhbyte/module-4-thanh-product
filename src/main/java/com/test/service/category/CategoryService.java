package com.test.service.category;

import com.test.model.Category;
import com.test.repository.ICategoryRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService implements ICategoryService{

  @Autowired
  private ICategoryRepository categoryRepository;

  @Override
  public Iterable<Category> findAll() {
    return categoryRepository.findAll();
  }

  @Override
  public Category save(Category category) {
    return categoryRepository.save(category);
  }

  @Override
  public void delete(Long id) {
    categoryRepository.deleteById(id);
  }

  @Override
  public Optional<Category> findById(Long id) {
    return categoryRepository.findById(id);
  }
}
