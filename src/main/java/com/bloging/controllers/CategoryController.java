package com.bloging.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bloging.payloads.ApiResponce;
import com.bloging.payloads.CategoryDto;
import com.bloging.services.CategoryService;


@RestController
@RequestMapping("/api/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
		
		CategoryDto responseCategoryDto = this.categoryService.createCategory(categoryDto);
	
		return new ResponseEntity<>(responseCategoryDto, HttpStatus.CREATED);
	}
	
	
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Integer categoryId){
		
		CategoryDto categoryDto = this.categoryService.getCategory(categoryId);
		
		return ResponseEntity.ok(categoryDto);
		
	}
 	
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategories(){
		
		List<CategoryDto> categoryDtos = this.categoryService.getAllCategories();
		
		return new ResponseEntity<List<CategoryDto>>(categoryDtos,HttpStatus.OK);
		
	}
	
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer categoryId) {
		
	 
	CategoryDto updateCategory = this.categoryService.updateCategory(categoryDto, categoryId);
		
		return ResponseEntity.ok(updateCategory);
	}
	
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponce> deleteCategory(@PathVariable Integer categoryId){
		
		this.categoryService.deleteCategory(categoryId);
		
		return new ResponseEntity<>(new ApiResponce("Category is deleted successfully with category ID " + categoryId, true), HttpStatus.OK);
		
	}
	
	
}
