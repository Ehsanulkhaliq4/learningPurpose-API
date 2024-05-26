package com.exam.examserver.service;

import com.exam.examserver.model.exam.Category;

import java.util.Set;

public interface CategoryService {
    //Add Category
    public Category addCategory(Category category);
    //Update category
    public Category updateCategory(Category category);
    //get All Category
    public Set<Category> getAllCategories();
    //get Category By Id
    public Category getCategoryById(Long categoryId);
    //delete Category
    public void deleteCategory(Long categoryId);
}
