package com.test.model.product;

import com.test.model.Category;
import javax.validation.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

public class ProductForm {
  private Long id;

  @NotEmpty
  private String name;

  private String description;

  private Category category;

  private MultipartFile image;

  public ProductForm() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public MultipartFile getImage() {
    return image;
  }

  public void setImage(MultipartFile image) {
    this.image = image;
  }
}
