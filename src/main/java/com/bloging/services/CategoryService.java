package com.bloging.services;

import java.util.List;

import com.bloging.payloads.CategoryDto;

public interface CategoryService {
	
	CategoryDto createCategory(CategoryDto categoryDto);
	
	CategoryDto getCategory(Integer categoryId);
	
	CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
	
	void deleteCategory(Integer categoryId);
	
	List<CategoryDto> getAllCategories();
}
