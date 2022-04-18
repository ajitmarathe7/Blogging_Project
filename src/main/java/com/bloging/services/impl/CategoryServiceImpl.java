package com.bloging.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bloging.entities.Category;
import com.bloging.exception.ResourceNotFoundException;
import com.bloging.payloads.CategoryDto;
import com.bloging.repository.CategoryRepo;
import com.bloging.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		
		Category category = this.modelMapper.map(categoryDto, Category.class);
		
		Category createdCategory = this.categoryRepo.save(category);

		// TODO Auto-generated method stub
		return this.modelMapper.map(createdCategory, CategoryDto.class);

	
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId ));
		
		return this.modelMapper.map(category, CategoryDto.class);
		
		
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		// TODO Auto-generated method stub
		
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));
		
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		
		Category updatedCategory=this.categoryRepo.save(category);
		return this.modelMapper.map(updatedCategory, CategoryDto.class);	
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		
		this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));
		// TODO Auto-generated method stub

		this.categoryRepo.deleteById(categoryId);
		
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		
		List<Category> categories = this.categoryRepo.findAll();
		
		List<CategoryDto> categoryDtos = categories.stream().map(category -> this.modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
		// TODO Auto-generated method stub
		return categoryDtos;
	}

}
